package lab.solupia.samsungsvc.api.duty.service;

import lab.solupia.samsungsvc.api.duty.dto.EmployeeDutyDTO;
import lab.solupia.samsungsvc.api.duty.mapper.MasterDutyMapper;
import lab.solupia.samsungsvc.api.duty.mapper.DutyInterfaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2019-02-07
 */
@Service
public class MasterDutyService {
    private final DutyInterfaceMapper dutyInterfaceMapper;
    private final MasterDutyMapper masterDutyMapper;

    @Autowired
    public MasterDutyService(DutyInterfaceMapper dutyInterfaceMapper, MasterDutyMapper masterDutyMapper) {
        this.dutyInterfaceMapper = dutyInterfaceMapper;
        this.masterDutyMapper = masterDutyMapper;
    }

    /**
     * 연동 대상 사용자 정보를 조회한다.
     */
    public List<EmployeeDutyDTO> findTargetUsers(List<String> dutyIds) {
        return dutyInterfaceMapper.findTargetUsers(dutyIds);
    }

    /**
     * 조회된 사용자의 직무 정보를 업데이트 한다.
     */
    public void updateUserDuty(EmployeeDutyDTO employeeDuty) {
        masterDutyMapper.updateUserDuty(employeeDuty);
    }

    public int updateGeneralDuty() {
        return masterDutyMapper.updateGeneralDuty();
    }
}
