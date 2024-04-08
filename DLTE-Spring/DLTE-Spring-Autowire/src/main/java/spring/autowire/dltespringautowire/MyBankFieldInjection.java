package spring.autowire.dltespringautowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.autowire.dltespringautowire.service.MyBank;

public class MyBankFieldInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("spring.autowire.dltespringautowire");
        context.refresh();
        MyBank myBank = context.getBean(MyBank.class);
        System.out.println(myBank.execute().toString());
    }
}
