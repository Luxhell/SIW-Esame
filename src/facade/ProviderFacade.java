package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Address;
import model.Product;
import model.Provider;

import java.util.ArrayList;
import java.util.List;

@Stateless
@EJB(name = "ejb/ProviderFacade", beanInterface = ProviderFacade.class, beanName = "ProviderFacade")
public class ProviderFacade {

	@PersistenceContext(unitName = "siw-esame-jsf")
	private EntityManager em;

	public ProviderFacade(EntityManager em) {
		this.em = em;
	}

	public ProviderFacade() {

	}

	public Provider createProvider(String partitaIVA, String telefono,
			String email, Address indirizzo) {
		Provider provider = new Provider();
		provider.setPartitaIVA(partitaIVA);
		provider.setTelefono(telefono);
		provider.setEmail(email);
		provider.setIndirizzo(indirizzo);
		this.em.persist(provider.getIndirizzo());
		this.em.persist(provider);
		return provider;
	}

	public Provider getProvider(Long id) {
		try {
			TypedQuery<Provider> query = this.em.createNamedQuery(
					"Provider.findProvider", Provider.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	} 
	
	public Provider getProvider(String email) {
		TypedQuery<Provider> query = this.em.createNamedQuery("Provider.findProviderByEmail", Provider.class);
	    query.setParameter("email", email);
	    try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	public Provider getProviderByPartitaIVA(String partitaIVA) {
		TypedQuery<Provider> query = this.em.createNamedQuery("Provider.findProviderByPartitaIVA", Provider.class);
	    query.setParameter("partitaIVA", partitaIVA);
	    try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}	

	public List<Provider> getAll() {
		return this.em.createNamedQuery("Provider.findAllProvider",
				Provider.class).getResultList();
	}

	public void updateProvider(Provider provider) {
		em.merge(provider);
	}

	private void deleteProvider(Provider provider) {
		em.remove(provider);
	}

	public void deleteProvider(Long id) {
		Provider provider = getProvider(id);
		deleteProvider(provider);
	}

	public List<Product> getAllMyProducts(String iva) {
		TypedQuery<Provider> query = this.em.createNamedQuery(
				"Provider.findProviderByPartitaIVA", Provider.class);
		query.setParameter("partitaIVA", iva);
		if(query.getSingleResult().getProdotti() == null)
			return (new ArrayList<Product>());
		return query.getSingleResult().getProdotti();
	}

	public Provider getProvider(String partitaIVA, String email) {
		try {
			TypedQuery<Provider> query = this.em.createNamedQuery(
					"Provider.findProvider2login", Provider.class);
			query.setParameter("partitaIVA", partitaIVA);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

}