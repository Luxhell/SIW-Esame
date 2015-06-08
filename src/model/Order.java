package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Orders")
@NamedQueries({
    @NamedQuery(name="Order.findOrder", query="SELECT o FROM Order o WHERE o.id = :id"),
    @NamedQuery(name="Order.findAllOrder", query="SELECT o FROM Order o"),
    @NamedQuery(name="Order.findOrders2Customer", query="SELECT o FROM Order o WHERE o.cliente.id = :id")
})
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 254)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAperturaOrdine;
	
	@Column(nullable = false, length = 254)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataChiusuraOrdine;
	
	@Column(length = 254)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEvasioneOrdine;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="CUSTOMER_ID")
	private Customer cliente;
	
	@OneToMany(mappedBy="ordine", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<OrderLine> lineeDiOrdine;
	
	public Order(){
		this.cliente = new Customer();
		this.lineeDiOrdine = new ArrayList<OrderLine>();
	}

	public boolean addLineaDiOrdine(OrderLine or){
		return this.lineeDiOrdine.add(or);
		
	}
	
	
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAperturaOrdine() {
		return this.dataAperturaOrdine;
	}

	public void setDataAperturaOrdine( Date creationTime) {
		this.dataAperturaOrdine = creationTime;
	}
	

	public Date getDataChiusuraOrdine() {
		return this.dataChiusuraOrdine;
	}

	public void setDataChiusuraOrdine(Date dataChiusuraOrdine) {
		this.dataChiusuraOrdine = dataChiusuraOrdine;
	}

	public Date getDataEvasioneOrdine() {
		return this.dataEvasioneOrdine;
	}

	public void setDataEvasioneOrdine( Date dataEvasioneOrdine) {
		this.dataEvasioneOrdine = dataEvasioneOrdine;
	}

	public Customer getCliente() {
		return this.cliente;
	}

	public void setCliente(Customer customer) {
		this.cliente = customer;
	}

	public List<OrderLine> getLineeDiOrdine() {
		return this.lineeDiOrdine;
	}

	public void setLineeDiOrdine(List<OrderLine> orderLines) {
		this.lineeDiOrdine = orderLines;
	}
	
	//FINE METODI GET E SET

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
		Order other = (Order) obj;
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
