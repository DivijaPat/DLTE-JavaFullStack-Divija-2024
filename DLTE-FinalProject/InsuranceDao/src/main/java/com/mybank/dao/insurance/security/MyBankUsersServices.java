package com.mybank.dao.insurance.security;//package com.payment.webservices.security;//package springjdbc.transaction.demo.security;

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

@Service
public class MyBankUsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(MyBankUsersServices.class);

    public MyBankUsers signUp(MyBankUsers myBankUsers){
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
        MyBankUsers users = findByUsernameStream(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }

    public List<MyBankUsers> findByUsername(){
        List<MyBankUsers> customer = jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",
                new BeanPropertyRowMapper<>(MyBankUsers.class));
        return customer;
    }

    public MyBankUsers findByUsernameStream(String username) {
        List<MyBankUsers> customerList = findByUsername();
        MyBankUsers customer = customerList.stream()
                .filter(customer1 -> customer1.getUsername().equals(username)).findFirst().orElse(null);
        return customer;
    }


    public void updateAttempts(MyBankUsers myBankUsers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{myBankUsers.getAttempts(),myBankUsers.getUsername()});
      logger.info("Attempts are updated");
    }

    public void updateStatus(MyBankUsers myBankUsers){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{myBankUsers.getUsername()});
        logger.info("Status has changed");
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
    }

