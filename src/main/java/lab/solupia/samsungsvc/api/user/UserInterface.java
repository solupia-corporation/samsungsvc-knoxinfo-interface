package lab.solupia.samsungsvc.api.user;

import lab.solupia.samsungsvc.api.CommonService;
import lab.solupia.samsungsvc.api.dept.constant.Target;
import lab.solupia.samsungsvc.api.dept.dto.DepartmentDTO;
import lab.solupia.samsungsvc.api.dept.service.DepartmentService;
import lab.solupia.samsungsvc.api.user.dto.PartnersUserDTO;
import lab.solupia.samsungsvc.api.user.service.UserService;
import lab.solupia.samsungsvc.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-20
 */
@Component
@Slf4j
public class UserInterface {
    private final static String EMP_URL = "findbycompdepartmentnumberwithpaging";

    private final DepartmentService departmentService;
    private final UserService userService;

    @Autowired
    public UserInterface(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    public void process(Target target) throws Exception {

        switch (target) {
            case PC6:
                log.info("$$ 본사 부서 정보 연동..");
                try {
                    List<DepartmentDTO> departmentList = departmentService.selectDepartment(target.name());

                    log.info("*******************************************************************************************************************************");

                    for (DepartmentDTO department : departmentList) {

                        log.info("삼성전자 서비스 사용자 부서 정보 연동");
                        log.info("삼성전자 서비스 사용자 회사코드 :: {}", target.name());
                        log.info("삼성전자 서비스 사용자 부서코드 :: {}", department.getDepartmentId());

                        String QUERY_STRING = "?cid=PC6REST0005&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=1";
                        String TOKEN = "07f46c7e39c3183a2eef761b3e2977de";

                        ResponseEntity<String> response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);

                        Long totalpage = CommonService.getToTalPage(response);

                        for (Long i = 1L; i <= totalpage; i++) {
                            QUERY_STRING = "?cid=PC6REST0005&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=" + i;
                            response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);
                            log.info("삼성전자 서비스 사용자 연동 결과 :: {}", response.getBody());

                            inertUser(response);
                        }
                    }
                } catch (Exception e) {
                    log.error("사용자 정보 연동 중 오류 발생 :: {}", e);

                    throw new Exception();
                }

                break;
            case PC8:
                log.info("$$ 자회사 사용자 정보 연동..");
                try {
                    List<DepartmentDTO> departmentList = departmentService.selectDepartment(target.name());

                    log.info("*******************************************************************************************************************************");

                    for (DepartmentDTO department : departmentList) {
                        log.info("삼성전자 서비스 사용자 자회사 부서 정보 연동");
                        log.info("삼성전자 서비스 사용자 자회사 회사코드 :: {}", target.name());
                        log.info("삼성전자 서비스 사용자 자회사 부서코드 :: {}", department.getDepartmentId());

                        String QUERY_STRING = "?cid=PC6REST0005&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=1";
                        String TOKEN = "07f46c7e39c3183a2eef761b3e2977de";

                        ResponseEntity<String> response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);

                        Long totalpage = CommonService.getToTalPage(response);

                        for (Long i = 1L; i <= totalpage; i++) {
                            QUERY_STRING = "?cid=PC6REST0005&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=" + i;
                            response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);
                            log.info("삼성전자 서비스 자회사 사용자 연동 결과 :: {}", response.getBody());

                            inertUser(response);
                        }
                    }
                } catch (Exception e) {
                    log.error("사용자 정보 연동 중 오류 발생 :: {}", e);

                    throw new Exception();
                }

                break;
            case PC9:
                log.info("$$ 삼성전자서비스 CS 사용자 정보 연동..");
                try {
                    List<DepartmentDTO> departmentList = departmentService.selectDepartment(target.name());

                    log.info("*******************************************************************************************************************************");

                    for (DepartmentDTO department : departmentList) {
                        log.info("삼성전자서비스 CS 사용자 부서 정보 연동");
                        log.info("삼성전자서비스 CS 사용자 회사코드 :: {}", target.name());
                        log.info("삼성전자서비스 CS 사용자 부서코드 :: {}", department.getDepartmentId());

                        String QUERY_STRING = "?cid=PC6REST0005&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=1";
                        String TOKEN = "07f46c7e39c3183a2eef761b3e2977de";

                        ResponseEntity<String> response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);

                        Long totalpage = CommonService.getToTalPage(response);

                        for (Long i = 1L; i <= totalpage; i++) {
                            QUERY_STRING = "?cid=PC6REST0005&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=" + i;
                            response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);
                            log.info("삼성전자서비스 CS 사용자 연동 결과 :: {}", response.getBody());

                            inertUser(response);
                        }
                    }
                } catch (Exception e) {
                    log.error("사용자 정보 연동 중 오류 발생 :: {}", e);

                    throw new Exception();
                }

                break;
//            case IC5:
//                log.info("$$ 협력사 사용자 정보 연동..");
//                try {
//                    List<DepartmentDTO> departmentList = departmentService.selectDepartment(target.name());
//
//                    log.info("*******************************************************************************************************************************");
//
//                    for (DepartmentDTO department : departmentList) {
//                        log.info("삼성전자 서비스 사용자 협력사 부서 정보 연동");
//                        log.info("삼성전자 서비스 사용자 협력사 회사코드 :: {}", target.name());
//                        log.info("삼성전자 서비스 사용자 협력사 부서코드 :: {}", department.getDepartmentId());
//
//                        String QUERY_STRING = "?cid=IC5REST0001&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=1";
//                        String TOKEN = "d4fd553209cc383e47920b77ec2db6b1";
//
//                        ResponseEntity<String> response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);
//
//                        log.info("삼성전자 서비스 협력사 사용자 연동 결과 PAGE :: 1 :: {}", response.getBody());
//
//                        Long totalpage = CommonService.getToTalPage(response);
//
//                        for (Long i = 1L; i <= totalpage; i++) {
//                            QUERY_STRING = "?cid=IC5REST0001&compCode=" + target.name() + "&departmentnumber=" + department.getDepartmentId() + "&page=" + i;
//                            response = HttpUtils.post("/employee/empsearch/api/basic/v1.0/" + EMP_URL + QUERY_STRING, String.class, TOKEN);
//                            log.info("삼성전자 서비스 협력사 사용자 연동 결과 PAGE :: {} :: {}", i, response.getBody());
//
//                            inertUser(response);
//                        }
//                    }
//                } catch (Exception e) {
//                    log.error("사용자 정보 연동 중 오류 발생 :: {}", e.getMessage());
//
//                    throw new Exception();
//                }
//
//                break;
//            case PARTNERS:
//                log.info("$$ 협력사 사용자 정보 연동..");
//
//                try {
//                    List<PartnersUserDTO> userList = userService.getPartnersUsers();
//
//                    // 2019.11.25 협력사DB에서는 해당 사용자의 부서정보만 가져오도록 변경.
//                    userService.updatePartnersUsers(userList);
//
//                    log.info("$$ 상담 협력사 사용자 정보 연동..");
//
//                    List<PartnersUserDTO> eduUserList = userService.getEduPartnersUsers();
//
//                    // 2019.11.25 협력사DB에서는 해당 사용자의 부서정보만 가져오도록 변경.
//                    userService.updatePartnersUsers(eduUserList);
//
//                    log.info("$$ 협력사 99N05 부서 사용자 정보 연동..");
//
//                    List<PartnersUserDTO> n05UserList = userService.get99N05PartnersUsers();
//
//                    // 2020.06.17(김대호 대리) 99N05 부서의 사용자는 신규 데이터 추가
//                    userService.insertPartnersUsers(n05UserList);
//                } catch (Exception e) {
//                    log.error("협력사 사용자 정보 연동 중 오류 발생 :: {}", e.getMessage());
//
//                    throw new Exception();
//                }
        }

        log.info("*******************************************************************************************************************************");
    }

    private void inertUser(ResponseEntity<String> response) throws IOException {
        List userList = CommonService.getResponseBody(response);

        if (userList.size() == 0) {
            log.info("사용자가 존재하지 않습니다.");
        } else {
            log.info("부서 사용자 {}건.. 부서코드 :: {}", userList.size(), ((HashMap<String, String>) userList.get(0)).get("departmentnumber"));
        }

        userList.forEach(u -> {
            HashMap<String, String> user = (HashMap<String, String>) u;

            log.info("*******************************************************************************************");
            log.info("사용자ID :: {}", user.get("userid"));
            log.info("*******************************************************************************************");

            if (userService.countUser(user.get("userid")) == 0) {
                userService.insertKnoxUser(user);
//                userService.mergePosition(user);
            } else {
                log.info("*******************************************************************************************************************************");
                log.info("사용자가 다시 한번 발견되었습니다.");
                log.info("사용자ID: {}", user.get("userid"));
                log.info("부서ID: {}", user.get("departmentnumber"));
                log.info("*******************************************************************************************************************************");
                userService.updateKnoxUser(user);
            }

//            user.put("departmentId", Objects.isNull(user.get("epsenddeptcode")) ? user.get("departmentnumber") : user.get("epsenddeptcode"));
//
//            userService.insertUserDepartmentMapping(user);
        });
    }
}
