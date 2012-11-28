package cz.boris.hibernate.repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.boris.hibernate.domain.Product;
import cz.boris.hibernate.domain.ProductFilter;

@Repository
@Transactional(readOnly = true)
public class HibernateProductRepository implements ProductRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Product> findAll() {
		@SuppressWarnings("unchecked")
		List<Product> products = sessionFactory.getCurrentSession()
				.createQuery("from Product p").list();
		return products;
	}

	public Product findProductById(Long id) {
		Product product = (Product) sessionFactory.getCurrentSession()
				.createCriteria(Product.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		return product;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Product createNewProduct(Product p) {		
		Product product = (Product) sessionFactory.getCurrentSession().save(p);
		return product;
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updatePrice(Long id, BigDecimal newPrice) {
		Product product = (Product) sessionFactory.getCurrentSession()
				.createQuery("from Product p where p.id = :id")
				.setParameter("id", id).uniqueResult();
		if (product != null) {
			product.setPrice(newPrice);
			sessionFactory.getCurrentSession().update(product);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> findProductWithFilter(ProductFilter filter) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Product.class);
		if (filter.getName() != null && filter.getName().length() > 0) {
			criteria.add(Restrictions.like("name", filter.getName()));
		}
		if (filter.getPrice() != null
				&& !filter.getPrice().equals(BigDecimal.ZERO)) {
			criteria.add(Restrictions.like("price", filter.getPrice()));
		}

		List<Product> products = (List<Product>) criteria.list();
		return (products.isEmpty() ? Collections.EMPTY_LIST : products);
	}

}
