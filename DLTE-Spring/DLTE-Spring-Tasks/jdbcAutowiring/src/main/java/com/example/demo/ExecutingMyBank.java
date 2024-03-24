package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutingMyBank {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext();
        configApplicationContext.scan("com.example.demo");
        configApplicationContext.refresh();
        MyBank myBank=configApplicationContext.getBean(MyBank.class);
        System.out.println(myBank.callFindAll());
    }
}