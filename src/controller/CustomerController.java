package controller;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.Address;
import model.Customer;
import facade.CustomerFacade;


@ManagedBean(name = "customerController")
public class CustomerController {
	
	private String nome;
	private String cognome;
	private String email;
	private Date dataNascita;
	private Date dataRegistrazione;
	private Boolean isAdmin;
	private String password;
	private String via;
	private String citta;
	private String stato;
	private String cap;
	private String regione;
	 
	private Customer customer;

	@EJB
	private CustomerFacade customerFacade;
	
	@ManagedProperty(value = "#{customerManager}")
	private CustomerManager session;
	
	
	public CustomerController(){
		
	}
	
	public String createCustomer(){	
		Address indirizzo=new Address();
		indirizzo.setVia(via);
		indirizzo.setCitta(citta);
		indirizzo.setCap(cap);
		indirizzo.setStato(stato);
		indirizzo.setRegione(regione);
		this.isAdmin = false;
		this.dataRegistrazione = new Date();

		this.customer = customerFacade.createCustomer(nome, cognome, email, dataNascita, indirizzo, password, isAdmin, dataRegistrazione);
		return "customer"; //pagina: provider.xhtml
	}
	
	public String createAdmin(){	
		Address indirizzo=new Address();
		indirizzo.setVia(via);
		indirizzo.setCitta(citta);
		indirizzo.setCap(cap);
		indirizzo.setStato(stato);
		indirizzo.setRegione(regione);
		this.isAdmin = true;
		this.dataRegistrazione = new Date();

		this.customer = customerFacade.createCustomer(nome, cognome, email, dataNascita, indirizzo, password, isAdmin, dataRegistrazione);
		return "customer"; //pagina: provider.xhtml
	}
	
    public String login(){
    	Customer c = this.customerFacade.getCustomer(email, password);
    	if(c==null)
    		return "loginErr"; //loginErr.xhtml;
    	else{
    		this.session.login(c);
    		if(this.session.isAdmin())
    			return "index_admin"; //index_admin.xhtml
    		return "index_customer"; //index_customer.xhtml
    	}	
    }
    
//    public String logout(){
//    	Customer c = this.session.getCurrent();
//    	if(c==null)
//    		return "loginErr"; //loginErr.xhtml;
//    	else{
//    		this.session.logout();
//    		if(this.session.isAdmin())
//    			return "logout"; //logout.xhtml
//    		return "logout"; //index_customer.xhtml
//    	}	
//    }


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerFacade getCustomerFacade() {
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	public CustomerManager getSession() {
		return session;
	}

	public void setSession(CustomerManager session) {
		this.session = session;
	}
	
	


}
