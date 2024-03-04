package basics.service;


public class App 
{
    public static void main( String[] args )
    {

        MyBankDatabase<CreditCard> creditCardMyBankDatabase= new MyBankDatabase<>();
        CreditCard card1=new CreditCard("6576874356",45000);
        CreditCard card2=new CreditCard("6874536756",5000);
        creditCardMyBankDatabase.create(card1);
        creditCardMyBankDatabase.create(card2);
        System.out.println("Credit card details "+creditCardMyBankDatabase.read(0)+" and"+creditCardMyBankDatabase.read(1));
        card1.setBalance(4500);
        creditCardMyBankDatabase.update(0,card1);
        System.out.println("Updated card details are: "+creditCardMyBankDatabase.read(0));
        creditCardMyBankDatabase.delete(1);
        System.out.println("Credit card 1 deleted");;
        MyBankDatabase<Transaction>transactionMyBankDatabase= new MyBankDatabase<>();
        Transaction transaction1=new Transaction(1,5000,"4Feb2024");
        Transaction transaction2=new Transaction(2,3400,"7August2024");
        transactionMyBankDatabase.create(transaction1);
        transactionMyBankDatabase.create(transaction2);
        System.out.println("transactions done");
        System.out.println("transactions are: "+transactionMyBankDatabase.read(0)+" and "+transactionMyBankDatabase.read(1));
        transaction1.setAmount(465654);
        transactionMyBankDatabase.update(0,transaction1);
        System.out.println("updated transaction is: "+transactionMyBankDatabase.read(0));
        transactionMyBankDatabase.delete(1);
        System.out.println("Transaction deleted");
    }
}
