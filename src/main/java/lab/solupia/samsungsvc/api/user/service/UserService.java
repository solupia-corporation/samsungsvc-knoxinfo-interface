package lab.solupia.samsungsvc.api.user.service;

import lab.solupia.samsungsvc.api.user.dto.PartnersUserDTO;
import lab.solupia.samsungsvc.api.user.mapper.PartnersUserMapper;
import lab.solupia.samsungsvc.api.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-20
 */
@Service
public class UserService {
    private final UserMapper userMapper;
//    private final PartnersUserMapper partnersUserMapper;

    @Autowired
    public UserService(UserMapper userMapper, PartnersUserMapper partnersUserMapper) {
        this.userMapper= userMapper;
//        this.partnersUserMapper = partnersUserMapper;
    }

    public int countUser(String userId) {
        return userMapper.countUser(userId);
    }

    public void insertKnoxUser(HashMap<String, String> user) {
        userMapper.insertKnoxUser(user);
    }
    public void updateKnoxUser(HashMap<String, String> user) {
        userMapper.updateKnoxUser(user);
    }

//    public void mergePosition(HashMap<String, String> user) {
//        userMapper.mergePosition(user);
//    }

//    public void insertUserDepartmentMapping(HashMap<String, String> user) {
//        userMapper.insertUserDepartmentMapping(user);
//    }

    public void deleteKnoxUser(){
        userMapper.deleteKnoxUser();
    }

//    public void deleteUserDepartmentMapping() {
//        userMapper.deleteUserDepartmentMapping();
//    }

//    public List<PartnersUserDTO> getPartnersUsers() {
//        return partnersUserMapper.getList();
//    }

//    public void insertPartnersUsers(List<PartnersUserDTO> userList) {
//        for (PartnersUserDTO user : userList) {
//            // 2020.06.17(김대호 대리) 협력사 사용자 데이터 중 사용 여부에 따라 재직 구분(Y->재직, N->퇴직)
//            user.setServeStatus("Y".equals(user.getUseYn()) ? "B" : "R");
//
//            userMapper.insertPartnersUser(user);
//
//            userMapper.insertPartnersUserDepartmentMapping(user);
//        }
//    }

//    public void updatePartnersUsers(List<PartnersUserDTO> userList) {
//        for (PartnersUserDTO user : userList) {
//            if (userMapper.countUserByEmployeeNumber(user.getEmployeeNumber()) == 0) {
//                // 2020.07.02(홍윤기 사원) TCKSF, UBSSF, EDU% 사용자 중 KNOX에서 추가안되면 추가하도록 (1순위 KNOX, 2순위 DB)
//                userMapper.insertPartnersUser(user);
//
//                user.setLoginId(user.getEmployeeNumber());
//
//                userMapper.insertPartnersUserDepartmentMapping(user);
//            } else {
//                user.setLoginId(userMapper.selectLoginIdByEmployeeNumber(user.getEmployeeNumber()));
//
//                userMapper.updatePartnersUserDepartmentMapping(user);
//            }
//
//            // 2020.06.17(김대호 대리) 협력사 사용자 데이터 중 사용 여부에 따라 재직 구분(Y->재직, N->퇴직)
//            user.setServeStatus("Y".equals(user.getUseYn()) ? "B" : "R");
//
//            userMapper.updatePartnersUserServeStatus(user);
//        }
//    }

//    public List<PartnersUserDTO> getEduPartnersUsers() {
//        return partnersUserMapper.getEduUsers();
//    }
//
//    public List<PartnersUserDTO> get99N05PartnersUsers() {
//        return partnersUserMapper.get99N05Users();
//    }
}
