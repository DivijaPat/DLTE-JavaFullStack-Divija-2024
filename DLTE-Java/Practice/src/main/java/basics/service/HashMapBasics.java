package basics.service;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasics {

        public static void main(String args[])
        {
            HashMap<Integer, String> hmap = new HashMap<Integer, String>();

            hmap.put(1, "Hi");
            hmap.put(5, "Hello");
            hmap.put(3, "World");

            System.out.println( hmap.get(1));

        }
    }

