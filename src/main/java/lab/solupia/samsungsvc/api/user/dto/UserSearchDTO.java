package lab.solupia.samsungsvc.api.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-11
 */
@Data
@Builder
public class UserSearchDTO {
    private String cid;
    private String fullname;
}
