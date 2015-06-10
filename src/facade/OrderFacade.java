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
    
	//ERRORE NEL CREATE. IL PERSIST NON VA
	public Order createOrder( Customer customer) {
		Customer cliente = this.em.find(Customer.class, customer.getId());
		Order order = new Order();
		order.setDataAperturaOrdine(new Date());
		order.setCliente(cliente);
		this.em.refresh(cliente);
		this.em.persist(order);
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
	
	public boolean checkProduct(Long idOrdine, Product product){
		Order temp = getOrder(idOrdine);
		for(OrderLine ol : temp.getLineeDiOrdine())
			if(ol.getProduct().equals(product)) return true;
		return false;
	}
	
	public void aggiornaQuantita(Long idOrdine, Product product){
		Order temp = getOrder(idOrdine);
		for(OrderLine ol : temp.getLineeDiOrdine())
			if(ol.getProduct().equals(product))
				ol.setQuantita(ol.getQuantita()+1);
		updateOrder(temp);
	}
	
	public void aggiundiRigaOrdine(Long idOrdine, Product product){
		Order temp = getOrder(idOrdine);
		OrderLine riga = new OrderLine();
		riga.setProduct(product);
		riga.setQuantita(1);
		riga.setPrezzoUnitario(product.getPrezzo());
		temp.getLineeDiOrdine().add(riga);
		updateOrder(temp);
	}

}