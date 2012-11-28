package cz.boris.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cz.boris.hibernate.domain.Category;
import cz.boris.hibernate.repository.CategoryRepository;

@ContextConfiguration("classpath:cz/boris/hibernate/app-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HibernateCategoryRepositoryTest {
	
	private CategoryRepository repository;
	
	@Autowired
	public void setCategoryRepository(CategoryRepository repository) {
		this.repository = repository;
	}

	@Test
	public void testCategory() {

		Category category = repository.findCategoryById(1L);
		assertNotNull(category);
		assertEquals("dairy", category.getName());

	}
	
	@Test
	public void findAll() {
		List<Category> all = repository.findAll();
		assertNotNull(all);
		assertEquals(4, all.size());
		assertEquals("dairy", all.get(0).getName());
	}

}
