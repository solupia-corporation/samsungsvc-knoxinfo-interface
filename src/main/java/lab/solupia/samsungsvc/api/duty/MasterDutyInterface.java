package lab.solupia.samsungsvc.api.duty;

import lab.solupia.samsungsvc.api.duty.dto.EmployeeDutyDTO;
import lab.solupia.samsungsvc.api.duty.mapper.MasterDutyMapper;
import lab.solupia.samsungsvc.api.duty.service.MasterDutyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2019-02-07
 */
@Component
@Slf4j
public class MasterDutyInterface {
    private final MasterDutyService masterDutyService;
    private final MasterDutyMapper masterDutyMapper;

    @Autowired
    public MasterDutyInterface(MasterDutyService masterDutyService, MasterDutyMapper masterDutyMapper) {
        this.masterDutyService = masterDutyService;
        this.masterDutyMapper = masterDutyMapper;
    }

    public void process() throws Exception {
        log.info("$$ 직무ID 조회");

        try {
            List<String> dutyIds = masterDutyMapper.selectDutyIds();

            log.info("$$ 직무ID 연동 대상 사용자 조회...");

            List<EmployeeDutyDTO> employeeDuties = masterDutyService.findTargetUsers(dutyIds);

            log.info("$$ {}건의 직무ID 연동 대상 사용자가 조회되었습니다...", employeeDuties.size());

            for (EmployeeDutyDTO employeeDuty : employeeDuties) {
                masterDutyService.updateUserDuty(employeeDuty);
            }

            log.info("$$ 기타 그외 사용자는 일반 직무로 업데이트 됩니다...");

            int generalDutyUserCount = masterDutyService.updateGeneralDuty();

            log.info("$$ {}명의 사용자가 일반직무로 업데이트 되었습니다.", generalDutyUserCount);
        } catch (Exception e) {
            log.error("직무 연동 중 오류 발생 :: {}", e.getMessage());

            throw new Exception();
        }
    }
}
