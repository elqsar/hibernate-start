package cz.boris.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.boris.hibernate.domain.Category;
import cz.boris.hibernate.domain.Product;
import cz.boris.hibernate.domain.ProductFilter;
import cz.boris.hibernate.repository.ProductRepository;

@ContextConfiguration("classpath:cz/boris/hibernate/app-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HibernateProductRepositoryTest {

	@Autowired
	private SessionFactory sessionFactory;

	private ProductRepository repository;

	@Autowired
	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}

	@Test
	public void testAddAndSelectProduct() {
		Product p = new Product();
		p.setName("Candy Milk");
		p.setPrice(new BigDecimal(5));
		p.setDescription("Good product");
		p.setCategory((Category) sessionFactory.getCurrentSession()
				.createQuery("from Category c where id = :id")
				.setParameter("id", 1L).uniqueResult());
		assertNotNull(p.getCategory());
		Long id = repository.createNewProduct(p);
		assertNotNull(id);
		Product product = repository.findProductById(p.getId());
		assertEquals("Candy Milk", product.getName());
		assertEquals("dairy", p.getCategory().getName());
	}

	@Test
	public void findWithFilterTest() {
		ProductFilter filter = new ProductFilter("cheese", null);
		Product product = repository.findProductWithFilter(filter).get(0);
		assertEquals("cheese", product.getName());
		filter.setPrice(new BigDecimal(2));
		filter.setName(null);
		// TODO: create new embedded database for test so test is ok after
		// product addition in production
		List<Product> products = repository.findProductWithFilter(filter);
		assertEquals(8, products.size());
	}
}
