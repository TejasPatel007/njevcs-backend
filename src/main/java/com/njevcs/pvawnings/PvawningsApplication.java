package com.njevcs.pvawnings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
@EnableCaching
@EnableAsync
public class PvawningsApplication {

    private static final Logger LOGGER = LogManager.getLogger(PvawningsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PvawningsApplication.class, args);
    }

}
