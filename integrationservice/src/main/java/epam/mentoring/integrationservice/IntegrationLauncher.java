package epam.mentoring.integrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IntegrationLauncher {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationLauncher.class);
    }

}