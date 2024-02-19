package basics.service;
import java.util.Arrays;
import java.util.Scanner;
public class minimumBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] balance = new double[20];
        for(int index=0;index<balance.length;index++){
            System.out.println("Enter the balance");
            balance[index]= scanner.nextDouble();
        }
        System.out.println(Arrays.toString(balance));
        minimumBalance.updateBalance(balance);
        System.out.println(Arrays.toString(balance));
    }
    public static void updateBalance(double[] minBalance){
        for(int index=0;index<minBalance.length;index++){
            if(minBalance[index]>=5000 && minBalance[index]<10000)
                  minBalance[index]=minBalance[index]-(minBalance[index]*0.03);
            else if(minBalance[index]>=1000 && minBalance[index]<5000)
                minBalance[index]=minBalance[index]-(minBalance[index]*0.05);


        }
        return;
    }
}
