package lab.solupia.samsungsvc.api.duty.mapper;

import lab.solupia.samsungsvc.api.config.datasource.annotation.MasterDuty;
import lab.solupia.samsungsvc.api.duty.dto.EmployeeDutyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2019-02-07
 */
@MasterDuty
public interface DutyInterfaceMapper {
    List<EmployeeDutyDTO> findTargetUsers(@Param("dutyIds") List<String> dutyIds);
}
