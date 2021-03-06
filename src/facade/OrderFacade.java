package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;

import java.util.Date;
import java.util.List;

@Stateless
@EJB(name="ejb/OrderFacade", beanInterface=OrderFacade.class, beanName="OrderFacade")
public class OrderFacade {
	
    @PersistenceContext (unitName = "siw-esame-jsf")
    private EntityManager em;
    
	public OrderFacade(EntityManager em){
		this.em=em;
	}
	
	public OrderFacade(){

	}
    
	public Order createOrder(Customer customer){
		Order order = new Order();
		order.setDataAperturaOrdine(new Date());
		customer = this.em.merge(customer);
		
		this.em.persist(order);
		customer.addOrdine(order);
		order.setCliente(customer);
		return order;
	}
	
	public Order getOrder(Long id) {
		TypedQuery<Order> query = this.em.createNamedQuery("Order.findOrder", Order.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}
	
	public List<Order> getAllOrder() {
        return this.em.createNamedQuery("Order.findAllOrder", Order.class).getResultList();
	}

    public void deleteOrder(Order order) {
    	Order temp = getOrder(order.getId());
        this.em.remove(temp);
    }
	
	public List<Order> getOrder2Customer(Long id){
		TypedQuery<Order> query = this.em.createNamedQuery("Order.findOrders2Customer", Order.class);
	    query.setParameter("id", id);
	    return query.getResultList();
	}
	
	public Order getOrdine(List<Order> ordini, Long id_ordine){
		if(ordini != null)
			for(Order o: ordini)
				if(o.getId()==id_ordine) return o;
		return null;
	}
	

	
	public boolean checkProduct(Order order, Product product){
		
		for(OrderLine orderLine : order.getLineeDiOrdine())
			if(orderLine.getProdotto().equals(product))
				return true;
		return false;
	}
	
	public void chiudiOrdine(Order order){
		order = this.em.merge(order);
		for(OrderLine o : order.getLineeDiOrdine()) {
			prelevaProdotto(o.getProdotto(), o.getQuantita());
		}
		order.setDataChiusuraOrdine(new Date());
		this.em.merge(order);
	}

	public List<Order> findAllOrderNotEvavaded() {
		 return this.em.createNamedQuery("Order.findAllOrderNotEvavaded", Order.class).getResultList();
	}

	public void evadiOrdine(Order order) {
		order.setDataEvasioneOrdine(new Date());
		this.em.merge(order);
	}
	
	private void prelevaProdotto(Product product, Integer qty){
		product = this.em.merge(product);
		product.setQuantita(product.getQuantita() - qty);
		this.em.merge(product);	
	}

	public void riaggiungiProdottoAlDB(Product prodotto, Integer quantita) {
		prodotto.setQuantita(prodotto.getQuantita()+quantita);
		this.em.merge(prodotto);
	}
	
}