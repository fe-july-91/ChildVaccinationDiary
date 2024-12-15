package team.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "team.project.repository")
public class ChildVaccinationDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildVaccinationDiaryApplication.class, args);
    }

}
