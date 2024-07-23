//package lab.solupia.samsungsvc.api.user.controller;
//
//import lab.solupia.samsungsvc.api.user.dto.UserSearchDTO;
//import lab.solupia.samsungsvc.utils.HttpUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
///**
// * @author jason, Moon (jason@solupia.co.kr)
// * @since 2018-12-11
// */
//@RestController
//@Slf4j
//public class UserRestController {
//    @GetMapping("/user/byfullname")
//    public String getUserByFullName() throws UnsupportedEncodingException {
//        UserSearchDTO userSearchDTO = UserSearchDTO.builder()
//                .cid("PC6REST0005")
//                .fullname("방승길")
//                .build();
//
//        String fullname = URLEncoder.encode("방승길", "UTF-8");
//
//        try {
//            return HttpUtils.post("/employee/empsearch/api/basic/v1.0/findbyfullname?cid=PC6REST00057&fullname=" + fullname, userSearchDTO, String.class).getBody();
//        } catch (HttpClientErrorException e) {
//            log.error("******************************************************************");
//            log.error("Status Text :: {}", e.getStatusText());
//            log.error("Status Code :: {}", e.getStatusCode());
//            log.error("ResponseBody :: {}", e.getResponseBodyAsString());
//            log.error("******************************************************************");
//
//            return null;
//        }
//    }
//}
