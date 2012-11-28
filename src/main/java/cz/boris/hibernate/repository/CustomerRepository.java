package cz.boris.hibernate.repository;

import java.util.List;

import cz.boris.hibernate.domain.Customer;

public interface CustomerRepository {

	List<Customer> findAll();

	Customer addNewCustomer(Customer customer);

	Customer getCustomerById(Long id);

}
