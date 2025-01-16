package org.example.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TimeSheetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeSheetApplication.class, args);
    }

}
