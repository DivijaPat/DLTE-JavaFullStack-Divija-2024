package basics.service;

import java.util.Vector;

public class VectorBasics {

    public static void main(String[] args)
    {
        Vector<Integer>  vectorName = new Vector<Integer>();
        for (int i = 1; i <= 5; i++)
            vectorName.add(i);
        System.out.println(vectorName);
        vectorName.remove(4);
        System.out.println(vectorName);
        int size=vectorName.size();
        for (int i = 0; i < size; i++)
            System.out.print(vectorName.get(i) + " ");
        vectorName.set(2,4);
        System.out.println("\n"+vectorName);
    }
}

