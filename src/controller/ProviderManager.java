package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Provider;

@ManagedBean(name = "providerManager")
@SessionScoped
public class ProviderManager {
	private Provider current;
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "logout"; // index.xhtml
		
	}

	public void login(Provider p) {
		this.current = p;
	}

	public boolean isLogged(){
		return (this.current != null);
	}
	

	
	
	
	//INIZIO METODI GET E SET
	
	public Provider getCurrent() {
		return this.current;
	}	
	
	//FINE METODI GET E SET

}