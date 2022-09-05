package com.chen.spike;

import com.chen.spike.o_annotation.PackageScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@PackageScan(
        entity = {"com.chen.spike.a_domain"},
        jpa = {"com.chen.spike.b_repository"}
)
@SpringBootApplication
public class SpikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpikeApplication.class, args);
    }

}
