package lab.solupia.samsungsvc.api.config.datasource.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <pre>
 *     본사, 자회사 직무정보 연동을 위한 데이터소스
 * </pre>
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-11-13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MasterDuty {
}
