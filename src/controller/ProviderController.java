package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.Address;
import model.Product;
import model.Provider;
import facade.ProviderFacade;


@ManagedBean(name = "providerController")
public class ProviderController {
	
	
	private String partitaIVA;
	private String telefono;
	private String email;
	private Address indirizzo;
	
	private String via;
	private String citta;
	private String stato;
	private String cap;
	private String regione;
	
	private Provider provider;
	private Product prodotto;
	
	@EJB
	private ProviderFacade providerFacade;
	
	@ManagedProperty(value = "#{providerManager}")
	private ProviderManager session;
	
	public ProviderController(){
		
	}
	
	public String createProvider(){
		this.indirizzo=new Address();
		this.indirizzo.setVia(via);
		this.indirizzo.setCitta(citta);
		this.indirizzo.setStato(stato);
		this.indirizzo.setCap(cap);
		this.indirizzo.setRegione(regione);
		this.provider = providerFacade.createProvider(partitaIVA.toLowerCase(), telefono, email.toLowerCase(), indirizzo);
		return "/portaleAdmin/provider.xhtml"; //pagina: provider.xhtml
	}
	
	public List<Product> getAllMyProducts(){
    	return this.session.getCurrent().getProdotti();
    }
    
    public List<Product> getAllProducts(){
    	try{
    		return this.session.getProviderTemp().getProdotti();
    	}catch(NullPointerException e){
    		return new ArrayList<Product>();
    	}
    }	
    
    public String dettagli(Provider provider){
		this.session.setProviderTemp(provider);
		this.provider = provider;
		return "provider.xhtml";
	}

	
	public String login(){
    	Provider p = this.providerFacade.getProvider(partitaIVA.toLowerCase(), email.toLowerCase());
    	if(p==null)
    		return "/loginProviderErr.xhtml"; //loginProviderErr.xhtml;
    	else{
    		this.session.login(p);
    		return "/portaleProvider/index.xhtml?faces-redirect=true"; //index_provider.xhtml
    	}	
    }
	
	public List<Provider> getAll(){
		return providerFacade.getAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//INIZIO METODI GET E SET

	public ProviderManager getSession() {
		return session;
	}

	public void setSession(ProviderManager session) {
		this.session = session;
	}



	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public ProviderFacade getProviderFacade() {
		return providerFacade;
	}

	public void setProviderFacade(ProviderFacade providerFacade) {
		this.providerFacade = providerFacade;
	}

	public Address getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Address indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	} 
	
	public Product getProdotto() {
		return prodotto;
	}

	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}
	
	

	//FINE METODI GET E SET

}
