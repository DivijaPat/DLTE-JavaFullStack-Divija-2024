package basics.service;

import java.util.ArrayList;

public class ArrayListExample {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList=new ArrayList<>(4);
        ArrayList<String> arrayList1=new ArrayList<>();  // without size-depends dynamically can grow and shrink
        ArrayList arrayList2=new ArrayList();
        arrayList.add(0,2);
        arrayList.add(3);
        arrayList1.add("Two");
        arrayList1.add(1,"Three");
        System.out.println("Display array list");
        arrayList.forEach((n)-> System.out.println(n));
        arrayList2.addAll(arrayList);
        System.out.println("\nDisplay list 2");
        arrayList2.forEach((n)-> System.out.println(n));
        arrayList2.addAll(2,arrayList1);
        System.out.println("\nDisplay list 2 after join");
        arrayList2.forEach((n)-> System.out.println(n));
        System.out.println("\nTo check elemnets");
        if(arrayList2.contains("Two"))
            System.out.println("True"); //checks if elements is present
        System.out.println("\nGet size "+arrayList2.size());
        System.out.println("\nElement at pos 3 in list 2: "+arrayList2.get(2));
        arrayList2.set(0,1);
        System.out.println("\nElement replacement of list 2: "+arrayList2);

        Object[] arrObject= arrayList1.toArray();

        System.out.println("\nArrayList to String Array");
        String[] arrString=arrayList1.toArray(new String[0]);
        for(String each:arrString) System.out.println(each+" ");
        Integer[] arrInteger = arrayList.toArray(new Integer[0]);
        System.out.println("\nArrayList to Integer Array");
        for(Integer each:arrInteger) System.out.println(each+" ");
        //Collections.sort();
    }
}
