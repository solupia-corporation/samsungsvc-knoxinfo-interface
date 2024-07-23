package lab.solupia.samsungsvc.api.user.mapper;

import lab.solupia.samsungsvc.api.config.datasource.annotation.Partners;
import lab.solupia.samsungsvc.api.user.dto.PartnersUserDTO;

import java.util.List;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2019-02-13
 */
@Partners
public interface PartnersUserMapper {
    List<PartnersUserDTO> getList();

    List<PartnersUserDTO> getEduUsers();

    List<PartnersUserDTO> get99N05Users();
}
