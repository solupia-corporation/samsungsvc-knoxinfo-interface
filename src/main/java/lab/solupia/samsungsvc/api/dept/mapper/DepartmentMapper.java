package lab.solupia.samsungsvc.api.dept.mapper;

import lab.solupia.samsungsvc.api.config.datasource.annotation.Master;
import lab.solupia.samsungsvc.api.dept.dto.DepartmentDTO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-19
 */
@Master
public interface DepartmentMapper {
    void insertKnoxDepartment(HashMap<String, String> departmentList);

    void insertCompanyDepartmentMapping(HashMap<String, String> departmentList);

    List<DepartmentDTO> selectDepartment(@Param("companyId") String companyId);
    // knox 추가 인사연동 데이터 테이블 비움
    void deleteKnoxDepartment();
//
//    void deleteCompanyDepartmentMapping();
//
//    void deleteCompany();
}
