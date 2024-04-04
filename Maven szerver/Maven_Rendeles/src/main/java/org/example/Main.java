package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AutoConfiguration
public class Main {

    public static void main(String[] args) {
        //Ez a kód kell a mainbe springboot alkalmazás indításához
        SpringApplication.run(Main.class, args);
    }
}