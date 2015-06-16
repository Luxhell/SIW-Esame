package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="product")
@NamedQueries({
    @NamedQuery(name="Product.findProduct", query="SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name="Product.findAllProduct", query="SELECT p FROM Product p"),
}) 
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 254)
	private String nome;
	
	@Column(nullable = false, length = 254)
	private String codice;
	
	@Column(length = 254)
	private String descrizione;
	
	@Column(nullable = false, length = 254)
	private Float prezzo;
	
	@Column(nullable = false, length = 254)
	private Float quantita;
	
	@ManyToMany(mappedBy="prodotti", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER )
	private List<Provider> fornitori;
	
	public Product() {
		this.fornitori = new ArrayList<Provider>();
	}
	
	public boolean addFornitore(Provider provider){
		return this.fornitori.add(provider);
	}
	

	public boolean isDisponibile(){
		return (this.getQuantita()>0);
	}
	
	
	
	//INIZIO METODI GET E SET

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Float getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public Float getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Float quantita) {
		this.quantita = quantita;
	}
	
	public List<Provider> getFornitori() {
		return this.fornitori;
	}

	public void setFornitori(List<Provider> fornitori) {
		this.fornitori = fornitori;
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
		Product other = (Product) obj;
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