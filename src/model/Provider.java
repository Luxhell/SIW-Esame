package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	@NamedQuery(name = "Provider.findProvider", query = "SELECT p FROM Provider p WHERE p.prodotti.id = :id "),
	@NamedQuery(name = "Provider.findProvider2login", query = "SELECT p FROM Provider p WHERE p.partitaIVA = :partitaIVA AND p.email = :email"),
	@NamedQuery(name = "Provider.findAllProvider", query = "SELECT p FROM Provider p"),
	
	@NamedQuery(name = "Provider.findProviderByEmail", query = "SELECT p FROM Provider p WHERE p.email = :email"),
	@NamedQuery(name = "Provider.findProviderByPartitaIVA", query = "SELECT p FROM Provider p WHERE p.partitaIVA = :partitaIVA")
})
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 254)
	private String partitaIVA;
	
	@Column(nullable = false, length = 254)
	private String telefono;
	
	@Column(nullable = false, length = 254)
	private String email;
	
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinColumn(name="ADDRESS_ID")
	private Address indirizzo;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name="provider2product", 
	joinColumns={@JoinColumn(name="provider_id")},
	inverseJoinColumns={@JoinColumn(name="product_id")})
	private List<Product> prodotti;
	
	public Provider(){
		this.prodotti = new ArrayList<Product>();
	}
	
	public void addProdotto(Product prodotto){
		this.prodotti.add(prodotto);
	}

	
	
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getPartitaIVA() {
		return this.partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String phoneNumber) {
		this.telefono = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Address address) {
		this.indirizzo = address;
	}

	public List<Product> getProdotti() {
		return this.prodotti;
	}

	public void setProdotti(List<Product> products) {
		this.prodotti = products;
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
		Provider other = (Provider) obj;
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
