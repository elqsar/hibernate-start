package cz.boris.hibernate.repository;

import java.util.List;

import cz.boris.hibernate.domain.Category;

public interface CategoryRepository {

	Category findCategoryById(Long id);
	
	List<Category> findAll();
	
}
