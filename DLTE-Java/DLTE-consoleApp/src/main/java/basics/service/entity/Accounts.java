package basics.service.entity;

public class Accounts {
    String username;
    Integer password;
    String address;
    Long mobile;
    String email;
    Double balance;

    public Accounts( ) {

    }

    public Accounts(String username, Integer password, String address, Long mobile, String email, Double balance) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public static void main(String[] args) {




    }
}
