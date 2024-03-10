package basics.service;

public class TransactionThreadMain{
    public static void main(String[] args) throws InterruptedException{
        TransactionAnalysis analysis=new TransactionAnalysis();
        Thread thread1=new Thread( analysis,"Divija");
        thread1.start();
        thread1.join();
        Thread thread2=new Thread(analysis,"Sunidhi");
        thread2.start();
        Thread thread3=new Thread(analysis,"Sudheesh");
        thread3.start();
        Thread thread4=new Thread(analysis,"Dishitha");
        thread4.start();
        Thread thread5=new Thread(analysis,"Bhargavi");
        thread5.start();
        Thread thread6=new Thread(analysis,"Anu");
        thread6.start();
        Thread thread7=new Thread(analysis,"Keerthana");
        thread7.start();
        Thread thread8=new Thread(analysis::displayAmount,"Meghana");
        thread8.start();
        Thread thread9=new Thread(analysis::displayBeneficiary,"Vani");
        thread9.start();
        Thread thread10=new Thread(analysis::displayRemarks,"Sandeep");
        thread10.start();

    }
}
