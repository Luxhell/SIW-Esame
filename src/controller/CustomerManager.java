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
		return "/logout.xhtml?faces-redirect=true"; // logout.xhtml
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
	
	public void setOrdineCorrente(Order order){
		this.ordineCorrente = order;
		
	}
	
	public void setOrder(Order order){
		this.ordineCorrente = order;
	}
	
	//FINE METODI GET E SET
}