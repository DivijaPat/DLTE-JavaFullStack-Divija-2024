package basics.service;

import basics.service.entity.Accounts;
import basics.service.middleware.FileStorageTarget;
import basics.service.remotes.StorageTarget;
import basics.service.services.BankServices;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App
{
    private static StorageTarget storageTarget;
    private static BankServices services;
    private static ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    public static void main( String[] args ) {
        App app = new App();
        System.out.println(resourceBundle.getString("greet.user"));
        Scanner scanner = new Scanner(System.in);
        String username,password;
        int choice;
        storageTarget = new FileStorageTarget();
        services = new BankServices(storageTarget);
        System.out.println("Enter your username");
        username = scanner.next();
        System.out.println("Enter your password");
        password = scanner.next();
        if (services.callverifyPassword(username,password)) {

            while(true) {
                System.out.println(resourceBundle.getString("menu.title"));
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        double amount;
                        System.out.println("Enter amount to be transferred");
                        amount = scanner.nextDouble();
                        System.out.println("Enter your password");
                        password = scanner.next();
//                            if (services.callverifyPassword(username, password))
//                                services.calltransfer(username, password, amount);

                        break;
                    case 2:
                        System.out.println("Deposit");

                        System.exit(0);
                    case 3:
                        System.out.println("withdraw");
                        System.exit(0);
                    case 4:
                        System.out.println("Logged out successfully");
                        System.exit(0);
                    default:
                        System.out.println("Wrong choice");
                        System.exit(0);
                }
            }

        }
    }
}
