package com.abridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * Database seed runner application to populate initial data.
 */
@SpringBootApplication
@Profile("seed")
public class DatabaseSeedRunnerApp {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DatabaseSeedRunnerApp.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        System.exit(SpringApplication.exit(application.run(args)));
    }
}
