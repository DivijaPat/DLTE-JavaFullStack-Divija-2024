package com.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch branch3=applicationContext.getBean("branch3", Branch.class);
        System.out.println(branch3.getIfsCode()+" "+branch3.getBranchName());
    }
}
