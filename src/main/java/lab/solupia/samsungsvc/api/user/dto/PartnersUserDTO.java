package lab.solupia.samsungsvc.api.user.dto;

import lombok.Data;

/**
 * 협력사 DB에서 가져올 사용자 데이터
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2019-02-13
 */
@Data
public class PartnersUserDTO {
    private String loginId;
    private String departmentId;
    private String employeeNumber;
    private String userName;
    private String useYn;
    private String serveStatus;
}
