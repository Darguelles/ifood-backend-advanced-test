package com.ifood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("classpath:application.yml")
public class IfoodMusicApplicationIntegrationTest {

    public static void main(String[] args) {
        SpringApplication.run(IfoodMusicApplicationIntegrationTest.class);
    }

}
