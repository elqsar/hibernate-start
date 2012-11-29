package cz.boris.hibernate.repository;

import java.math.BigDecimal;
import java.util.List;

import cz.boris.hibernate.domain.Product;
import cz.boris.hibernate.domain.ProductFilter;

public interface ProductRepository {

	List<Product> findAll();

	Product findProductById(Long id);

	Long createNewProduct(Product p);

	void updatePrice(Long id, BigDecimal newPrice);

	List<Product> findProductWithFilter(ProductFilter filter);

}
