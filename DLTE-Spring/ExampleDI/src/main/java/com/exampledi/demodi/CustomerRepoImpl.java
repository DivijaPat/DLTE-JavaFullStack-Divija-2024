package com.exampledi.demodi;

public class CustomerRepoImpl implements CustomerRepository {
    public void save(Customer customer) {
        // Implementation to save customer to the database
        System.out.println("Saving customer: " + customer.getName());
    }
}
