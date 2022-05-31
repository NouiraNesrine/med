package com.med.illnessservice;

import com.med.sharedservice.AxonConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConf.class})
public class IllnessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IllnessServiceApplication.class, args);
    }

}
