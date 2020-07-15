package com.muye.kl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.muye.kl.filter")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
