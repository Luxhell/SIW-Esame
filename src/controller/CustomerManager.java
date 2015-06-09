package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Customer;
import model.Order;

@ManagedBean(name = "customerManager")
@SessionScoped
public class CustomerManager {
	private Customer current;

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
		Order o = new Order();
		this.current.addOrdine(o);
		return "products_customer"; //products_customer.xhtml
	}
	
	
	
	
	
	
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public Customer getCurrent() {
		return this.current;
	}
	
	//FINE METODI GET E SET
}