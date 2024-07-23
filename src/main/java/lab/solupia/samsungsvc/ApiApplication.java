package lab.solupia.samsungsvc;

import lab.solupia.samsungsvc.api.ExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Slf4j
public class ApiApplication {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context =
             SpringApplication.run(ApiApplication.class, args)) {
        }
    }

    @Bean
    @Profile({"local", "developement", "production"})
    public CommandLineRunner runner(ExecuteService executeService) {
        return (args -> executeService.execute());
    }

}
