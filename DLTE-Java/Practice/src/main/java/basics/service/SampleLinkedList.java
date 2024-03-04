package basics.service;

import java.util.LinkedList;

public class SampleLinkedList {
    public static void main(String[] args) {
        LinkedList<String> myList=new LinkedList<>();
        myList.add("Hello");
        myList.add(1,"Good");
        myList.add(2,"evening");
        System.out.println(myList);
        myList.set(2,"morning");
        System.out.println(myList);
        myList.remove(0);
        System.out.println(myList);
    }
}
