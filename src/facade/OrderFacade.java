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
import facade.OrderLineFacade;

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
    
	//ERRORE NEL CREATE. IL PERSIST NON VA
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

	public void updateOrder(Order order) {
        em.merge(order);
	}
	
    private void deleteOrder(Order order) {
        em.remove(order);
    }

	public void deleteOrder(Long id) {
		Order order = getOrder(id);
        deleteOrder(order);
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
		Long idProdotto = product.getId();
		for(OrderLine orderLine : order.getLineeDiOrdine())
			if(orderLine.getProduct().getId() == (idProdotto))
				return true;
		return false;
	}
	
	/*public void aggiornaQuantita(Order order, Product product){
		for(OrderLine ol : order.getLineeDiOrdine())
			if(ol.getProduct().equals(product))
				ol.setQuantita(ol.getQuantita()+1);
		updateOrder(order);
	}*/
	
	public OrderLine getRigaOrdine(Order order, Product product){
		for(OrderLine ol : order.getLineeDiOrdine())
			if(ol.getProduct().equals(product))
				return ol;
		return null;
	}
}