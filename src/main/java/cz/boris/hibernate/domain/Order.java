package cz.boris.hibernate.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer_order")
public class Order extends AbstractEntity {

	private BigDecimal amount;

	@Column(name = "date_created")
	@Temporal(TemporalType.DATE)
	private Date dateCreated;

	@Column(name = "confirmation_number")
	private Integer confirmationNUmber;

	@ManyToMany
	@JoinTable(name = "ordered_product", joinColumns = @JoinColumn(name = "customer_order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private List<Product> products;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getConfirmationNUmber() {
		return confirmationNUmber;
	}

	public void setConfirmationNUmber(Integer confirmationNUmber) {
		this.confirmationNUmber = confirmationNUmber;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Order [amount=" + amount + ", dateCreated=" + dateCreated
				+ ", confirmationNUmber=" + confirmationNUmber + "]";
	}

}
