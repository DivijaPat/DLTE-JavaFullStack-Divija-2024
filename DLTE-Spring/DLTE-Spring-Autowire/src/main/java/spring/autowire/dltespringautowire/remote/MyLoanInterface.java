package spring.autowire.dltespringautowire.remote;

import spring.autowire.dltespringautowire.model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface MyLoanInterface {
  List<Loan> myLoan = Stream.of(new Loan(456345,"divija",12000,"open","personal loan"),
          new Loan(762930,"Sunidhi",40070,"closed","personal loan"),
          new Loan(378210,"Sanjana",59000,"closed","home loan"),
          new Loan(890651,"Sudheesh",10000,"open","home loan")).collect(Collectors.toList());
  List<Loan> findAll();
}
