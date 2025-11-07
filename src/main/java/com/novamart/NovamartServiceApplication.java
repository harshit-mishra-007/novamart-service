package com.novamart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import io.mongock.runner.springboot.EnableMongock;

@ServletComponentScan
@EnableMongock
@SpringBootApplication(scanBasePackages = { "com.novamart" })
public class NovamartServiceApplication {
    public static void main(String[] args) {
    	SpringApplication.run(NovamartServiceApplication.class, args);
    }
}