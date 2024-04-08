package com.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class App {
    public static void main( String[] args )
    {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
        Branch branch1=beanFactory.getBean("branch4", Branch.class);
        System.out.println(branch1.getBranchContact()+" "+branch1.getBranchName());
        Branch branch2=beanFactory.getBean("branch1",Branch.class);
        System.out.println(branch2);
    }
}
