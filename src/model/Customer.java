package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "Customer.findAllCustomer", query = "SELECT c FROM Customer c"),
	
	@NamedQuery(name = "Customer.findCustomer", query = "SELECT c FROM Customer c WHERE c.id = :id"),
	
	@NamedQuery(name = "Customer.findCustomerByEmailANDByPassword", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password"),
	
	@NamedQuery(name = "Customer.findCustomerByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
})
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false,length = 254)
	private String nome;

	@Column(nullable = false,length = 254)
	private String cognome;

	@Column(nullable = false,length = 254)
	private String email;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascita;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	
	@Column(nullable = false,length = 254)
	private Boolean isAdmin;

	@OneToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="ADDRESS_ID")
	private Address indirizzo;
	
	@Column(nullable = false,length = 254)
	private String password;
	
	@OneToMany(mappedBy="cliente", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Order> ordini;

	public Customer(){
		this.ordini = new ArrayList<Order>();
	}
	
	public Order getOrdineSpecifico(Long id){
			for(Order o: this.ordini){
				if(o.getId().equals(id))
					return o;
			}
		return null;		
	}
	
	public boolean addOrdine(Order order){
		return this.ordini.add(order);
	}
	
	
	
	
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String firstName) {
		this.nome = firstName;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String lastName) {
		this.cognome = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return this.dataNascita;
	}

	public void setDataNascita(java.util.Date dateOfBirth) {
		this.dataNascita = dateOfBirth;
	}

	public Date getDataRegistrazione() {
		return this.dataRegistrazione;
	}

	public void setDataRegistrazione(java.util.Date registrationDate) {
		this.dataRegistrazione = registrationDate;
	}

	public Address getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Address address) {
		this.indirizzo = address;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrdini() {
		return this.ordini;
	}

	public void setOrdini(List<Order> orders) {
		this.ordini = orders;
	}


	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
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
		Customer other = (Customer) obj;
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
