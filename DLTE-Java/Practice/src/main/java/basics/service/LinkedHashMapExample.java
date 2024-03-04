package basics.service;

import java.util.LinkedHashMap;

public class LinkedHashMapExample {

        public static void main(String a[])
        {

            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
            linkedHashMap.put("one", "Hello");
            linkedHashMap.put("two", "Welcome");
            linkedHashMap.put("four", "Good morning");

            System.out.println(linkedHashMap);

            System.out.println("Getting value for key 'one': " + linkedHashMap.get("one"));

            System.out.println("Size of the map: " + linkedHashMap.size());

            System.out.println("Is map empty? "+ linkedHashMap.isEmpty());

            System.out.println("Contains key 'two'? " + linkedHashMap.containsKey("two"));

            System.out.println("delete element 'one': " + linkedHashMap.remove("one"));

            System.out.println("Mappings of LinkedHashMap : "+linkedHashMap);
        }
    }


