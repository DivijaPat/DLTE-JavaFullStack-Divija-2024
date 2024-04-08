package com.ioc.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
        Branch akashBranch=beanFactory.getBean("branch4", Branch.class);
        System.out.println(akashBranch.getBranchContact()+" "+akashBranch.getBranchName());
        Branch elroyBranch=beanFactory.getBean("branch3",Branch.class);
        System.out.println(elroyBranch);
    }

}
