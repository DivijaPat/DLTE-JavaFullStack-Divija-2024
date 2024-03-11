package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class AppTest
{
    static List<Loan> loanList=new ArrayList<>();
    static List<Loan> loan=new ArrayList<>();

    @BeforeClass
    public static void initialize(){
        loanList.add(new Loan(123,1000D,new Date(2023,07,15),"Divi","open",9876543L));
        loanList.add(new Loan(567,2000D,new Date(2023,07,14),"Aru","closed",9676543L));
        loanList.add(new Loan(754,3000D,new Date(2023,06,13),"Ann","open",2754543L));
        loanList.add(new Loan(234,4000D,new Date(2023,05,12),"Den","open",9557543L));

    }
    @Test
    public void testClosed()  {
        String expectedLoanStatus="closed";
        assertNotEquals("Expected test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
    }

    @Test
    public void testOpen() {
        // Assuming open loans have a status of "open"
        String expectedLoanStatus="open";
        assertNotEquals("Expected test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(3).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(4).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(5).getLoanStatus());
    }
    @Test(timeout =2000)
    public void testTime() throws InterruptedException {
        Thread.sleep(100);
        assertTrue(loanList.size()>0);
    }



}
