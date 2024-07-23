package lab.solupia.samsungsvc.api.config.datasource;

import lab.solupia.samsungsvc.api.config.datasource.annotation.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author jason, Moon
 * @since 2016. 12. 13.
 */
public abstract class MyBatisConfig {
    static final String BASE_PACKAGE = "lab.solupia.samsungsvc.*/**";
    private static final String CONFIG_LOCATION_PATH = "classpath:mybatis/config/mybatis-config.xml";
    private static final String MAPPER_LOCATIONS_PATH = "classpath:mybatis/mapper/*/*.xml";

    void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
    }
}

@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = Master.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
class MasterMyBatisConfig extends MyBatisConfig {

    @Bean
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, masterDataSource);

        return sessionFactoryBean.getObject();
    }
}

@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = Partners.class, sqlSessionFactoryRef = "partnersSqlSessionFactory")
class PartnersMyBatisConfig extends MyBatisConfig {

    @Bean
    public SqlSessionFactory partnersSqlSessionFactory(@Qualifier("partnersDataSource") DataSource partnersDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, partnersDataSource);
        return sessionFactoryBean.getObject();
    }
}

@Configuration
@MapperScan(basePackages = MyBatisConfig.BASE_PACKAGE, annotationClass = MasterDuty.class, sqlSessionFactoryRef = "masterDutySqlSessionFactory")
class MasterDutyMyBatisConfig extends MyBatisConfig {

    @Bean
    public SqlSessionFactory masterDutySqlSessionFactory(@Qualifier("masterDutyDataSource") DataSource masterDutyDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, masterDutyDataSource);
        return sessionFactoryBean.getObject();
    }
}