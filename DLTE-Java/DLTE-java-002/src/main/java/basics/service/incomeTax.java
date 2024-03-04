package basics.service;

import java.util.Scanner;

public class incomeTax {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double salary=0.0;
        System.out.println("enter your salary");
        salary=scanner.nextDouble();
        System.out.println("Enter your choice:\n1.Old Task Regime\n2.New Task Regime");
        int choice=scanner.nextInt();
        switch(choice){
            case 1:
                if(salary>0 && salary<250000)
                    System.out.println("old tax rate is Exempted");

                else if(salary>=250000 && salary<500000)
                    System.out.println("old tax rate is "+(0.05*salary));


                else if(salary>=500000 && salary<750000)
                    System.out.println("old tax rate is "+(0.20*salary));

                else if(salary>=750000 && salary<1000000)
                    System.out.println("old tax rate is "+(0.20*salary));

                else if(salary>=1000000 && salary<1250000)
                    System.out.println("old tax rate is "+(0.30*salary));

                else if(salary>=1250000 && salary<1500000)
                    System.out.println("old tax rate is "+(0.30*salary));

                else
                    System.out.println("old tax rate is "+(0.30*salary));
                break;

            case 2:
                if(salary>0 && salary<250000)
                    System.out.println("new task rate is Exempted");

                else if(salary>=250000 && salary<500000)
                    System.out.println("new tax rate is "+(0.05*salary));


                else if(salary>=500000 && salary<750000)
                    System.out.println("new tax rate is "+(0.10*salary));

                else if(salary>=750000 && salary<1000000)
                    System.out.println("new tax rate is "+(0.15*salary));

                else if(salary>=1000000 && salary<1250000)
                    System.out.println("new tax rate is "+(0.20*salary));

                else if(salary>=1250000 && salary<1500000)
                    System.out.println("new tax rate is "+(0.25*salary));

                else
                    System.out.println("new tax rate is "+(0.30*salary));

                break;

            default:
                System.exit(0);
        }



    }

}


