package basics.jaxb;

import java.util.Date;

public class Transaction {
        private String user;
        private String receiver;
        private Date date;
        private double amount;

        public Transaction() {
        }

        public Transaction(String user,String receiver,Date date, double amount) {
            this.user = user;
            this.receiver=receiver;
            this.date = date;
            this.amount = amount;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
}
