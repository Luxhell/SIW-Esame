package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import facade.ProviderFacade;
 
@FacesValidator("validator.NewProviderValidatorPartitaIVA")
public class NewProviderValidatorPartitaIVA implements Validator{
	private ProviderFacade providerFacade;
 
	public NewProviderValidatorPartitaIVA(){
		
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		String partitaIVA = value.toString();
		
		try {
			this.providerFacade = (ProviderFacade) new InitialContext().lookup("java:comp/env/ejb/ProviderFacade");
		} catch (NamingException e) {
			FacesMessage msg = 
					new FacesMessage("Errore.", 
							"Errore generico.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
		}
		
		if(this.providerFacade.getProviderByPartitaIVA(partitaIVA) != null){
			FacesMessage msg = 
					new FacesMessage("Utente già esistente", 
							"Esiste già un fornitore con questa partita iva");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
		}
	}
	
}