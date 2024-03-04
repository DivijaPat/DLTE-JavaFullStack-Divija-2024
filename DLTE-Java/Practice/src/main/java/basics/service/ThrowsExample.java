package basics.service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ThrowsExample {
    public static void main(String[] args) {
        try{
            Age age=new Age();
            age.display();
        } catch(Exception exception) {
            System.out.println("Wrong Age Inputted!!!");
        }
    }
}

class Age{
    Scanner scanner=new Scanner(System.in);
    int age;
    void display() throws InputMismatchException{
        System.out.println("Enter the age");
        age=scanner.nextInt();
        if(age!=18){
            throw new InputMismatchException("Wrong input");
        }
    }
}
