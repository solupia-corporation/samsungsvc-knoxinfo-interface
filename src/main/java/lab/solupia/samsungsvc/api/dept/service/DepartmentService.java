package lab.solupia.samsungsvc.api.dept.service;

import lab.solupia.samsungsvc.api.dept.dto.DepartmentDTO;
import lab.solupia.samsungsvc.api.dept.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-19
 */
@Service
public class DepartmentService {
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public void insertKnoxDepartment(HashMap<String, String> department) {
        departmentMapper.insertKnoxDepartment(department);
    }

//    public void insertCompanyDepartmentMapping(HashMap<String, String> department) {
//        departmentMapper.insertCompanyDepartmentMapping(department);
//    }

    public List<DepartmentDTO> selectDepartment(String companyId) {
        return departmentMapper.selectDepartment(companyId);
    }

    public void deleteKnoxDepartment() {
        departmentMapper.deleteKnoxDepartment();
    }
//
//    public void deleteCompany() {
//        departmentMapper.deleteCompany();
//
//    }
//    public void deleteCompanyDepartmentMapping() {
//        departmentMapper.deleteCompanyDepartmentMapping();
//    }
}
