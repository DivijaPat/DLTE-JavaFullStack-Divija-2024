package com.mybank.dao.insurance.security;//package com.payment.webservices.security;//package springjdbc.transaction.demo.security;

import com.mybank.dao.insurance.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ResourceBundle;
@Service
public class MyBankUsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(MyBankUsersServices.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    public Customer signUp(Customer myBankUsers){
       jdbcTemplate.update("insert into  MYBANK_APP_CUSTOMER values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,?)",new Object[]{
               myBankUsers.getCustomerName(),
                myBankUsers.getCustomerAddress(),myBankUsers.getCustomerStatus(),
                myBankUsers.getCustomerContact(), myBankUsers.getUsername(), myBankUsers.getPassword(),
               myBankUsers.getAttempts()
        });
        return myBankUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer users = findByUsernameStream(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }

    public List<Customer> findByUsername(){
        List<Customer> customer = jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",
                new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }

    public Customer findByUsernameStream(String username) {
        List<Customer> customerList = findByUsername();
        Customer customer = customerList.stream()
                .filter(customer1 -> customer1.getUsername().equals(username)).findFirst().orElse(null);
        if(customer==null){
            throw new UsernameNotFoundException(username);
        }
        return customer;
    }


    public void updateAttempts(Customer myBankUsers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{myBankUsers.getAttempts(),myBankUsers.getUsername()});
      logger.info(resourceBundle.getString("attempts.update"));
    }

    public void updateStatus(Customer myBankUsers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{myBankUsers.getUsername()});
        logger.info(resourceBundle.getString("status.change"));
    }

    public List<Integer> getAccountNumberByCustomerId(int customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForList(sql, new Object[]{customerId}, Integer.class);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public String getCustomerName(String user) {
        try {
            String sql = "SELECT c.CUSTOMER_NAME FROM mybank_app_customer c WHERE c.username =  ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{user}, String.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    }

