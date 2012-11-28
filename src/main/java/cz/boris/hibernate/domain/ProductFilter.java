package cz.boris.hibernate.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductFilter implements Serializable {

	private static final long serialVersionUID = 6549377737223220371L;

	private String name;

	private BigDecimal price;

	public ProductFilter(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public ProductFilter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
