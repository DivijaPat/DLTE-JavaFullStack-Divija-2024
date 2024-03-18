package basics.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionCreation {

        public static void main(String[] args) {
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(new Transaction("Divija","Medhini", new Date(), 500.0));
            transactions.add(new Transaction("Avani","Arjun", new Date(), 200.0));
            transactions.add(new Transaction("Akshira","Annapoorna", new Date(), 350.0));

            TransactionsWrapper wrapper = new TransactionsWrapper();
            wrapper.setTransactions(transactions);

            try {
                JAXBContext context = JAXBContext.newInstance(TransactionsWrapper.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(wrapper, new File("transactions.xml"));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        @XmlRootElement
        public static class TransactionsWrapper {
            private List<Transaction> transactions;

            @XmlElement(name = "transaction")
            public List<Transaction> getTransactions() {
                return transactions;
            }

            public void setTransactions(List<Transaction> transactions) {
                this.transactions = transactions;
            }
        }


    }

