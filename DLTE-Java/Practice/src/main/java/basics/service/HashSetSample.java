package basics.service;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetSample {
        public static void main(String args[])
        {

            HashSet<String> hs = new HashSet<String>();
            hs.add("Loan");
            hs.add("Debit");
            hs.add("Credit");
            hs.add("Fund");
            hs.add("Transaction");
            Iterator<String> itrName = hs.iterator();
            while (itrName.hasNext()) {
                System.out.println(itrName.next());
            }
        }
    }


