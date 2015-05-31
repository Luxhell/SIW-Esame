package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Address;

import java.util.List;



@Stateless
@EJB(name="ejb/AddressFacade", beanInterface=AddressFacade.class, beanName="AddressFacade")
public class AddressFacade {
	
    @PersistenceContext (unitName = "siw-esame-jsf")
    private EntityManager em;
    
	public AddressFacade(EntityManager em){
		this.em=em;
	}
	
	public AddressFacade(){

	}
    
	public Address createAddress(String street, String city, String state, String zipcode, String country) {
		Address address = new Address();
		address.setVia(street);
		address.setCitta(city);
		address.setStato(state);
		address.setCap(zipcode);
		address.setRegione(country);
		this.em.persist(address);
		return address;
	}
	
	public Address getAddress(Long id) {
		TypedQuery<Address> query = this.em.createNamedQuery("Address.findAddress", Address.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}
	
	public List<Address> getAllAddress() {
		return this.em.createNamedQuery("Address.findAllAddress", Address.class).getResultList();

	}

	public void updateAddress(Address address) {
        em.merge(address);
	}
	
    private void deleteAddress(Address address) {
        em.remove(address);
    }

	public void deleteAddress(Long id) {
		Address address = getAddress(id);
        deleteAddress(address);
	}

}