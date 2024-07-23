package lab.solupia.samsungsvc.api.dept;

import lab.solupia.samsungsvc.api.CommonService;
import lab.solupia.samsungsvc.api.ProcessMethod;
import lab.solupia.samsungsvc.api.dept.constant.Target;
import lab.solupia.samsungsvc.api.dept.service.DepartmentService;
import lab.solupia.samsungsvc.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-19
 */
@Component
@Slf4j
public class DepartmentInterface {
    private final DepartmentService departmentService;

    private final static String DEPT_URL = "findbyeporganizationnumberwithpaging";

    @Autowired
    public DepartmentInterface(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void process(Target target, ProcessMethod processMethod) throws Exception {
        ResponseEntity<String> response;

        switch (target) {
            case PC6:
                log.info("본사 부서 정보 연동..");
                try {
                    response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=PC6&page=1", String.class, "07f46c7e39c3183a2eef761b3e2977de");
                    Long totalpage = CommonService.getToTalPage(response);

                    for (Long i = 1L; i <= totalpage; i++) {
                        response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=PC6&page=" + i, String.class, "07f46c7e39c3183a2eef761b3e2977de");
                        log.info("삼성전자 서비스 본사 부서 정보 Response :: {}", response.getBody());

                        executeMethod(response, target.name(), processMethod);
                    }

                } catch (Exception e) {
                    log.error("본사 부서 정보 연동 중 오류 발생 :: {}", e);
                    

                    throw new Exception();
                }
                break;
            case PC8:
                log.info("자회사 부서 정보 연동..");
                try {
                    response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=PC8&page=1", String.class, "07f46c7e39c3183a2eef761b3e2977de");
                    Long totalpage = CommonService.getToTalPage(response);
                    for (Long i = 1L; i <= totalpage; i++) {
                        response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=PC8&page=" + i, String.class, "07f46c7e39c3183a2eef761b3e2977de");
                        log.info("삼성전자 서비스 본사 부서 정보 Response :: {}", response.getBody());

                        executeMethod(response, target.name(), processMethod);
                    }

                } catch (Exception e) {
                    log.error("본사 자회사 정보 연동 중 오류 발생 :: {}", e);

                    throw new Exception();
                }
                break;
            case PC9:
                log.info("삼성전자서비스 CS 정보 연동..");
                try {
                    response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=PC9&page=1", String.class, "07f46c7e39c3183a2eef761b3e2977de");
                    Long totalpage = CommonService.getToTalPage(response);
                    for (Long i = 1L; i <= totalpage; i++) {
                        response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=PC9&page=" + i, String.class, "07f46c7e39c3183a2eef761b3e2977de");
                        log.info("삼성전자 서비스 본사 부서 정보 Response :: {}", response.getBody());

                        executeMethod(response, target.name(), processMethod);
                    }

                } catch (Exception e) {
                    log.error("삼성전자서비스 CS 정보 연동 중 오류 발생 :: {}", e);

                    throw new Exception();
                }
                break;
//            case IC5:
//                log.info("협력사 부서 정보 연동..");
//                try {
//                    response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=IC5&page=1", String.class, "07f46c7e39c3183a2eef761b3e2977de");
//                    Long totalpage = CommonService.getToTalPage(response);
//                    for (Long i = 1L; i <= totalpage; i++) {
//                        response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + DEPT_URL + "?cid=PC6REST0005&eporganizationnumber=IC5&page=" + i, String.class, "07f46c7e39c3183a2eef761b3e2977de");
//                        log.info("삼성전자 서비스 협력사 부서 정보 Response :: {}", response.getBody());
//
//                        executeMethod(response, target.name(), processMethod);
//                    }
//
//                } catch (Exception e) {
//                    log.error("본사 자회사 정보 연동 중 오류 발생 :: {}", e.getMessage());
//
//                    throw new Exception();
//                }
//                break;
//            case PARTNERS:
//                log.info("협력사 부서 정보 연동..");
//                log.info("연동 예정..");
            default:
                break;
        }
    }

    private void executeMethod(ResponseEntity<String> response, String companyId, ProcessMethod processMethod) throws IOException {
        switch (processMethod) {
            case INSERT:
                inertDepartment(response, companyId);
                break;
            case UPDATE:
        }
    }

    private void inertDepartment(ResponseEntity<String> response, String companyId) throws IOException {
        List departmentList = CommonService.getResponseBody(response);

        departmentList.forEach(e -> {
            HashMap<String, String> department = (HashMap<String, String>) e;
            if (Objects.nonNull(department.get("ephighdeptnumber")) && !StringUtils.pathEquals("TOP", department.get("departmentnumber"))) {
                log.info("*******************************************************************************************");
                log.info("부서코드 :: {}", department.get("departmentnumber"));
                log.info("상위부서코드 :: {}", department.get("ephighdeptnumber"));
                log.info("부서명 :: {}", department.get("ou"));
                log.info("현재 페이지 :: {}", department.get("currentpage"));
                log.info("총 페이지 :: {}", department.get("totalpage"));
                log.info("*******************************************************************************************");
                department.put("companyId", companyId);
                departmentService.insertKnoxDepartment(department);
//                departmentService.insertCompanyDepartmentMapping(department);
            }
        });
    }
}
