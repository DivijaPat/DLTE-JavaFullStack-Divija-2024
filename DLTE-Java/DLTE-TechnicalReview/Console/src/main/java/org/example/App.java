package org.example;


public class App {

        public void performOperations() {
            int value = 5;
           add(value, 3);
            System.out.println("perform operations");

        }

        private void add(int original, int other) {
            int value= original + other;
            square(value);
            System.out.println("add method");
        }

        private void square(int number) {
            int result=number*number;
            System.out.println("result"+result);
            return;
        }

        public static void main(String[] args) {
            App myObject = new App();
            myObject.performOperations();;
        }
    }





