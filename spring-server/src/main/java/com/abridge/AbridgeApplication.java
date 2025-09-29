package com.abridge;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Main application class for Abridge.
 */
@SpringBootApplication
@PropertySource("classpath:application.yml")
public class AbridgeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AbridgeApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
