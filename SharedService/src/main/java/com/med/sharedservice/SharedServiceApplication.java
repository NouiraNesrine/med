package com.med.sharedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConf.class})
public class SharedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedServiceApplication.class, args);
    }

}
