package com.jrt.quizsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class QuizSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(QuizSystemApplication.class, args);

    }

}
