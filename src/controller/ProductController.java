package controller;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.Product;
import model.Provider;
import facade.ProductFacade;


@ManagedBean(name = "productController")
public class ProductController {
	
	
	private String nome;
	private String codice;
	private String descrizione;
	private Float prezzo;
	private Float quantita;
	private Provider fornitore;
	
	private Product prodotto;
	
	@ManagedProperty(value = "#{providerManager}")
	private ProviderManager session;
	
	@ManagedProperty(value = "#{customerManager}")
	private CustomerManager session2;

	@EJB
	private ProductFacade prodottoFacade;
	
	
	public ProductController(){
		
	}
	
	public String createProductAdmin(){		
			this.prodotto = prodottoFacade.createProduct(nome, codice, descrizione, prezzo, quantita, fornitore);
			return "/portaleAdmin/product.xhtml"; //pagina: product.xhtml
	}
	
	public String createProductProvider(){
			this.prodotto = prodottoFacade.createProduct(nome, codice, descrizione, prezzo, quantita, this.session.getCurrent());
			return "/portaleProvider/product.xhtml"; //pagina: product.xhtml
	}
	
	public List<Product> getAll(){
			return this.prodottoFacade.getAll();
	}
		
	public String dettagli(Product prodotto){
		this.prodotto = prodotto;
		return "product.xhtml"; //product.xhtml
	}
	
	public List<Provider> getMyProvider(Product prodotto){
		this.prodotto = prodotto;
		return this.prodotto.getFornitori();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public String getNome() {
		return nome;
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public Float getQuantita() {
		return quantita;
	}

	public CustomerManager getSession2() {
		return session2;
	}

	public void setSession2(CustomerManager session2) {
		this.session2 = session2;
	}

	public Product getProdotto() {
		return prodotto;
	}

	public ProductFacade getProdottoFacade() {
		return prodottoFacade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public void setQuantita(Float quantita) {
		this.quantita = quantita;
	}

	public void setFornitore(Provider fornitore) {
		this.fornitore = fornitore;
	}

	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}

	public void setProdottoFacade(ProductFacade prodottoFacade) {
		this.prodottoFacade = prodottoFacade;
	}
		
	public Provider getFornitore() {
		return this.fornitore;
	}

	public ProviderManager getSession() {
		return this.session;
	}

	public void setSession(ProviderManager session) {
		this.session = session;
	}
	
	

	//FINE METODI GET E SET
	

}
