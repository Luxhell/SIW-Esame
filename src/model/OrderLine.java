package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderLines")
@NamedQueries({
    @NamedQuery(name="OrderLine.findOrderLine", query="SELECT ol FROM OrderLine o WHERE ol.id = :id"),
    @NamedQuery(name="OrderLine.findAllOrderLine", query="SELECT ol FROM OrderLine ol")
})
public class OrderLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 254)
	private Float prezzoUnitario;
	
	@Column(nullable = false, length = 254)
	private Integer quantita;
	
	@ManyToOne (cascade = {CascadeType.PERSIST})
	@JoinColumn(name="ORDER_ID")
	private Order ordine;
	
	@OneToOne (cascade = {CascadeType.PERSIST})
	@JoinColumn(name="PRODUCT_ID")
	private Product prodotto;
	
	public OrderLine(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(Float unitPrice) {
		this.prezzoUnitario = unitPrice;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantity) {
		this.quantita = quantity;
	}

	public Order getOrder() {
		return ordine;
	}

	public void setOrder(Order order) {
		this.ordine = order;
	}

	public Product getProduct() {
		return prodotto;
	}

	public void setProduct(Product product) {
		this.prodotto = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.id.toString();
	}

}