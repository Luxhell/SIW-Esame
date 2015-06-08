package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Product;
import model.Provider;
import java.util.List;

@Stateless
@EJB(name="ejb/ProductFacade", beanInterface=ProductFacade.class, beanName="ProductFacade")
public class ProductFacade {
	
    @PersistenceContext (unitName = "siw-esame-jsf")
    private EntityManager em;
    
	public ProductFacade(EntityManager em){
		this.em=em;
	}
	
	public ProductFacade(){

	}
    
	public Product createProduct(String name, String code, String description, Float price, Float qty, Provider prov) {
		Product product = new Product();
		product.setNome(name);
		product.setCodice(code);
		product.setDescrizione(description);
		product.setPrezzo(price);
		product.setQuantita(qty);
		this.em.persist(product);
		product.addFornitore(prov);
		prov.addProdotto(product);
		this.em.merge(prov);
		this.em.merge(product);
		return product;
	}
	
	public Product getProduct(Long id) {
		TypedQuery<Product> query = this.em.createNamedQuery("Product.findProduct", Product.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}
	
	public List<Product> getAll() {
		return this.em.createNamedQuery("Product.findAllProduct", Product.class).getResultList();
		
	}

	public void updateProduct(Product product) {
        em.merge(product);
	}
	
    private void deleteProduct(Product product) {
        em.remove(product);
    }

	public void deleteProduct(Long id) {
        Product product = getProduct(id);
        deleteProduct(product);
	}
	
	public List<Provider> getFornitori(Long id){
		Product product = getProduct(id);
		return product.getFornitori();
	}
}