package lab.solupia.samsungsvc.api.config.datasource;


import com.zaxxer.hikari.HikariDataSource;
import lab.solupia.samsungsvc.api.config.datasource.prop.DatabaseProperties;
import lab.solupia.samsungsvc.api.config.datasource.prop.MasterDatabaseProperties;
import lab.solupia.samsungsvc.api.config.datasource.prop.MasterDutyDatabaseProperties;
import lab.solupia.samsungsvc.api.config.datasource.prop.PartnersDatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018. 11. 13.
 */
@Slf4j
public abstract class DataSourceConfig {
    DataSource makeDataSource(DatabaseProperties databaseProperties) {
        log.info("MAKE DATASOURCE: {}", databaseProperties.toString());

        return DataSourceBuilder.create().type(HikariDataSource.class)
                .driverClassName(databaseProperties.getDriverClassName())
                .url(databaseProperties.getUrl())
                .username(databaseProperties.getUsername())
                .password(databaseProperties.getPassword())
                .build();
    }
}

@Configuration
@EnableConfigurationProperties(MasterDatabaseProperties.class)
class MasterDatabaseConfig extends DataSourceConfig {

    private final MasterDatabaseProperties masterDatabaseProperties;

    @Autowired
    public MasterDatabaseConfig(MasterDatabaseProperties masterDatabaseProperties) {
        this.masterDatabaseProperties = masterDatabaseProperties;
    }

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource dataSource() {
        return makeDataSource(masterDatabaseProperties);
    }
}

@Configuration
@EnableConfigurationProperties(PartnersDatabaseProperties.class)
class PartnersDatabaseConfig extends DataSourceConfig {

    private final PartnersDatabaseProperties partnersDatabaseProperties;

    @Autowired
    public PartnersDatabaseConfig(PartnersDatabaseProperties partnersDatabaseProperties) {
        this.partnersDatabaseProperties = partnersDatabaseProperties;
    }

    @Bean(name = "partnersDataSource")
    public DataSource dataSource() {
        return makeDataSource(partnersDatabaseProperties);
    }
}

@Configuration
@EnableConfigurationProperties(MasterDutyDatabaseProperties.class)
class MasterDutyDatabaseConfig extends DataSourceConfig {

    private final MasterDutyDatabaseProperties masterDutyDatabaseProperties;

    @Autowired
    public MasterDutyDatabaseConfig(MasterDutyDatabaseProperties masterDutyDatabaseProperties) {
        this.masterDutyDatabaseProperties = masterDutyDatabaseProperties;
    }

    @Bean(name = "masterDutyDataSource")
    public DataSource dataSource() {
        return makeDataSource(masterDutyDatabaseProperties);
    }
}