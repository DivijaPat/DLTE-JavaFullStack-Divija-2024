package org.application;
import basics.service.Basket;

import java.util.Arrays;

public class App
{
    public static void main( String[] args )
    {

       Basket basket=new Basket();
       String[] result=basket.display();
        System.out.println(Arrays.toString(result));
    }
}
