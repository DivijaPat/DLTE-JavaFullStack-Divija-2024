package basics.service;

interface MyInterface {
    void myMethod(int x);
}



    public static void main(String[] args) {

        MyInterface myLambda = (int x) -> {
            System.out.println("My method with input: " + x);
        };
        myLambda.myMethod(10);
    }
}



