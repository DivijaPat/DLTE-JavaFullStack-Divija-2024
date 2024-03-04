package basics.service;

import java.util.ArrayList;

public class SampleArrayList {
    public static void main(String[] args) {

       Details detail1=new Details( "Robert",25);
       Details detail2=new Details( "Emma",47);
       //array list object
        ArrayList<Details> myList=new ArrayList<Details>();
        myList.add(detail1);
        myList.add(detail2);
        System.out.println(myList.get(0).name);
        System.out.println(myList.get(1).age);


    }
}

class Details {
    String name;
    int age;

    public  Details(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

