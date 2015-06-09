package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Customer;
import model.Order;
import model.Product;

@ManagedBean(name = "customerManager")
@SessionScoped
public class CustomerManager {
	private Customer current;
	private Order ordine;

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "logout"; // logout.xhtml
	}

	public void login(Customer c) {
		this.current = c;
	}

	public boolean isAdmin() {
		return this.current.getIsAdmin();
	}

	public boolean isLogged(){
		return (this.current != null);
	}

	public String visualizzaProdotti(){
		this.ordine = new Order();
		return "products_customer"; //products_customer.xhtml
	}
	
	public void aggiungiAlCarrello (Product product){
    		if(this.ordine.checkProduct(product)) // metodo per vedere se esiste già quel prodotto nell ordine
    	
    		this.ordine.aggiornaQuantita(product);
    	else
    		this.ordine.aggiundiRigaOrdine(product);
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public Customer getCurrent() {
		return this.current;
	}
	
	//FINE METODI GET E SET
}