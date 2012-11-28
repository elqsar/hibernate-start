package cz.boris.hibernate.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.boris.hibernate.domain.Category;

@Repository
@Transactional(readOnly = true)
public class HibernateCategoryRepository implements CategoryRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Category findCategoryById(Long id) {
		Category category = (Category) sessionFactory.getCurrentSession()
				.createQuery("from Category c where c.id = :id")
				.setParameter("id", id).uniqueResult();
//		Category category = (Category) sessionFactory.getCurrentSession()
//				.createCriteria(Category.class)
//				.add(Restrictions.eq("id", id)).uniqueResult();
		return category;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Category c").list();
	
	}

}
