package lab.solupia.samsungsvc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab.solupia.samsungsvc.api.dept.service.DepartmentService;
import lab.solupia.samsungsvc.api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CommonService {

    private final DepartmentService departmentService;
    private final UserService userService;

    @Autowired
    public CommonService(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    public void truncateData(){
//        userService.deleteUserDepartmentMapping();
        userService.deleteKnoxUser();
//        departmentService.deleteCompanyDepartmentMapping();
        departmentService.deleteKnoxDepartment();
//        departmentService.deleteCompany();
    }


    public static Long getToTalPage(ResponseEntity<String> response) {
        Long totalpage = 0L;
        List responseBody = null;
        try {
            responseBody = getResponseBody(response);
        } catch (IOException e) {
            log.error("responseErrpr", e);
        }
        if(Objects.nonNull(responseBody) && responseBody.size() > 0) {
            HashMap<String, String> department = (HashMap<String, String>)responseBody.get(0);
            totalpage = Long.valueOf(department.get("totalpage"));
        }
        return totalpage;
    }

    public static List getResponseBody(ResponseEntity<String> response) throws IOException {
        return Arrays.asList(new ObjectMapper().readValue(response.getBody(), HashMap[].class));
    }
}
