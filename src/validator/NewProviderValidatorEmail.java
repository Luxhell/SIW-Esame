package validator;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import facade.ProviderFacade;
 
@FacesValidator("validator.NewProviderValidatorEmail")
public class NewProviderValidatorEmail implements Validator{
 
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
 
	private Pattern pattern;
	private Matcher matcher;
	private ProviderFacade providerFacade;
 
	public NewProviderValidatorEmail(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		String email = value.toString();
		matcher = pattern.matcher(email);
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("Errore Validazione.", 
						"Hai scritto una Email non valida.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
		try {
			this.providerFacade = (ProviderFacade) new InitialContext().lookup("java:comp/env/ejb/ProviderFacade");
		} catch (NamingException e) {
			FacesMessage msg = 
					new FacesMessage("Errore.", 
							"Errore generico.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
		}
		
		if(this.providerFacade.getProvider(email) != null){
			FacesMessage msg = 
					new FacesMessage("Utente già esistente", 
							"Esiste già un fornitore con questo indirizzo email");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
		}
	}
	
}