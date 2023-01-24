package com.doro.filey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Iterator;

@SpringBootApplication
public class FileyApplication {

    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(FileyApplication.class, args);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames) {
//            System.out.println(name);
//        }
    }
}
