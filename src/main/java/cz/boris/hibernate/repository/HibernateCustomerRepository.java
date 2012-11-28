package cz.boris.hibernate.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.boris.hibernate.domain.Customer;

@Repository
@Transactional
public class HibernateCustomerRepository implements CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Customer")
				.list();
	}

	public Customer addNewCustomer(Customer customer) {
		return (Customer) sessionFactory.getCurrentSession().save(customer);
	}

	@Transactional(readOnly = true)
	public Customer getCustomerById(Long id) {
		return (Customer) sessionFactory.getCurrentSession()
				.createQuery("from Customer c where c.id = :id")
				.setParameter("id", id).uniqueResult();
	}

}
