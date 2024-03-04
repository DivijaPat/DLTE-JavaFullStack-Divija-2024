package basics.service;

public class ThrowExample {
    public static void AttemptLimit(int count){
        if(count>5){
            throw new ArithmeticException("Account Blocked!!!");
        }
    }

    public static void main(String[] args) {
        try{
            AttemptLimit(26);
        }
        catch(ArithmeticException arithmeticException){
            System.out.println(arithmeticException.getMessage());
        }
    }
}
