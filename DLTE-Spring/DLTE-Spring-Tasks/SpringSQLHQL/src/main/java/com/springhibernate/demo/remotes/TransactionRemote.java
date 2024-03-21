package com.springhibernate.demo.remotes;

import com.springhibernate.demo.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRemote extends JpaRepository<Transactions,Integer>{
    @Query(value = "select * from transactions where user_name=:user and transaction_type=:type",nativeQuery = true)
    List<Transactions> findByUserAndType(String user, String type);
    @Query("from Transactions where transactionAmount between :amount1 and :amount2")
    List<Transactions> findByRangeOfTransactionAmount(double amount1, double amount2);

}
