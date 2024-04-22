package com.mybank.dao.insurance.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Customer {
    @NotNull(message = "{user.customerId.null}")
    @Digits(integer = 8, fraction = 0, message = "{user.customerId.null}")
    private Integer customerId;
    @NotNull(message = "{user.customerName.null}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{user.customerName.invalid}")
    private String customerName;
    @NotNull(message = "{user.customerAddress.null}")
    private String customerAddress;
    @NotNull(message = "{user.customerStatus.null}")
    @Pattern(regexp = "^(open|closed)$", message = "{user.customerStatus.invalid}")
    private String customerStatus;
    @NotNull(message = "{user.customerContact.null}")
    @Pattern(regexp = "\\d{10}", message = "{user.customerContact.invalid}")
    private Long customerContact;
    @NotNull(message = "{user.username.null}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "{user.username.invalid}")
    private String username;
    @NotNull(message = "{user.password.null}")
    @Size(min = 8, message = "{user.password.invalid}")
    private String password;
    private Integer attempts;

    public Customer(int customerId, String customerName, String customerAddress, String customerStatus, long customerContact, String username, String password,Integer attempts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
        this.attempts=attempts;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(long customerContact) {
        this.customerContact = customerContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
