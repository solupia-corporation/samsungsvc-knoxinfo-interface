package lab.solupia.samsungsvc.api.duty.mapper;

import lab.solupia.samsungsvc.api.config.datasource.annotation.Master;
import lab.solupia.samsungsvc.api.duty.dto.EmployeeDutyDTO;

import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2019-02-07
 */
@Master
public interface MasterDutyMapper {
    void updateUserDuty(EmployeeDutyDTO employeeDuty);

    int updateGeneralDuty();

    List<String> selectDutyIds();
}
