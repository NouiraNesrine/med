package com.med.symptomsservice;

import com.med.sharedservice.AxonConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConf.class})
public class SymptomsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SymptomsServiceApplication.class, args);
    }

}
