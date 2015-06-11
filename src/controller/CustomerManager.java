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
	private Order ordineCorrente;

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

	public void nuovoOrdine(Order order){
		this.ordineCorrente = order;
	}
	
	public boolean ordineAperto(){
		return (this.ordineCorrente!=null);
	}
	
	
	
	
	
	
	
	
	
	
		
	//INIZIO METODI GET E SET
	
	public Customer getCurrent() {
		return this.current;
	}
	
	public Order getOrdineCorrente(){
		return this.ordineCorrente;
	}
	
	//FINE METODI GET E SET
}