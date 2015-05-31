package controller;


import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Address;
import facade.AddressFacade;

@ManagedBean(name = "addressController")
public class AddressController {
	
	//Aggiunto commento
	
	private String via;
	private String citta;
	private String stato;
	private String cap;
	private String regione;
	
	private Address indirizzo;

	@EJB
	private AddressFacade addressFacade;
	
	
	public AddressController(){
		
	}
	
//	public String createAddress(){
//		this.address = addressFacade.createAddress(street, city, state, zipcode, country);
//		return "address"; //pagina: address.xhtml
//	}
	
	public List<Address> getAll(){
		List<Address> temp = this.addressFacade.getAllAddress();
		return(temp!=null ? temp : new LinkedList<Address>());
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

	public Address getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Address indirizzo) {
		this.indirizzo = indirizzo;
	}

	public AddressFacade getAddressFacade() {
		return addressFacade;
	}

	public void setAddressFacade(AddressFacade addressFacade) {
		this.addressFacade = addressFacade;
	}
	

}
