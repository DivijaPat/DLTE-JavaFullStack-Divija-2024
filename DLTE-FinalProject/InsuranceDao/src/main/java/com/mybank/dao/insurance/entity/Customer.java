package com.mybank.dao.insurance.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class Customer implements UserDetails {
    private Integer customerId;
    private String customerName;
    private String customerAddress;
    private String customerStatus;
    private Long customerContact;
    private String username;
    private String password;
    private Integer attempts;
    private final Integer maxAttempts=3;

    public Customer() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }
    public Long getCustomerContact() {
        return customerContact;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerStatus() {
        return customerStatus;
    }
    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }
    public Integer getAttempts() {
        return attempts;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public Customer(Integer customerId, String customerName, String customerAddress, String customerStatus, Long customerContact, String username, String password, Integer attempts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
        this.attempts = attempts;
    }
}

