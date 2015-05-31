package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.Date;

import model.Address;
import model.Customer;

import java.util.List;

@Stateless
@EJB(name="ejb/CustomerFacade", beanInterface=CustomerFacade.class, beanName="CustomerFacade")
public class CustomerFacade {
	
    @PersistenceContext (unitName = "siw-esame-jsf")
    private EntityManager em;
    
	public CustomerFacade(EntityManager em){
		this.em=em;
	}
	
	public CustomerFacade(){

	}
    
	public Customer createCustomer(String nome, String cognome, String email, Date dataNascita, Address address, String pwd, boolean isAdmin, Date dataRegistrazione) {
		Customer customer = new Customer();
		customer.setNome(nome);
		customer.setCognome(cognome);
		customer.setEmail(email);
		customer.setDataNascita(dataNascita);
		customer.setDataRegistrazione(dataRegistrazione);
		customer.setIsAdmin(isAdmin);
		customer.setPassword(pwd);
		customer.setIndirizzo(address);
		this.em.persist(customer.getIndirizzo());
		this.em.persist(customer);
		return customer;
	}
	
	public Customer getCustomer(Long id) {
		TypedQuery<Customer> query = this.em.createNamedQuery("Customer.findCustomer", Customer.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}	
	
	public Customer getCustomer(String email, String password) {
		TypedQuery<Customer> query = this.em.createNamedQuery("Customer.findCustomerByEmailANDByPassword", Customer.class);
	    query.setParameter("email", email);
	    query.setParameter("password", password);
    	try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Customer getCustomer(String email) {
		TypedQuery<Customer> query = this.em.createNamedQuery("Customer.findCustomerByEmail", Customer.class);
	    query.setParameter("email", email);
	    try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	public List<Customer> getAllCustomer() {
		return this.em.createNamedQuery("Customer.findAllCustomer", Customer.class).getResultList();

	}

	public void updateCustomer(Customer customer) {
        em.merge(customer);
	}
	
    private void deleteCustomer(Customer customer) {
        em.remove(customer);
    }

	public void deleteCustomer(Long id) {
		Customer customer = getCustomer(id);
        deleteCustomer(customer);
	}

}