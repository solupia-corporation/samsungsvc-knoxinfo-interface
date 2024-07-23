package lab.solupia.samsungsvc.api.user.mapper;

import lab.solupia.samsungsvc.api.config.datasource.annotation.Master;
import lab.solupia.samsungsvc.api.user.dto.PartnersUserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-20
 */
@Master
public interface UserMapper {
    int countUser(@Param("userId") String userId);

    int countUserByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    void insertKnoxUser(HashMap<String, String> user);
    void updateKnoxUser(HashMap<String, String> user);

//    void mergePosition(HashMap<String, String> user);

//    void insertUserDepartmentMapping(HashMap<String, String> user);

    void deleteKnoxUser();

//    void deleteUserDepartmentMapping();

    /**
     * 협력사 사용자를 저장한다.
     */
    void insertPartnersUser(PartnersUserDTO user);

    /**
     * 사용자의 로그인ID를 조회한다.
     */
    String selectLoginIdByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    /**
     * 협력사 사용자의 부서 매핑 정보를 저장한다.
     */
    void updatePartnersUserDepartmentMapping(PartnersUserDTO user);

    /*
    * 협력사 사용자의 재직 구분 정보를 수정한다.
    * */
    void updatePartnersUserServeStatus(PartnersUserDTO user);

    /**
     * 협력사(부서 99N05) 사용자의 부서 매핑 정보를 저장한다.
     */
    void insertPartnersUserDepartmentMapping(PartnersUserDTO user);
}
