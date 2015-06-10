package facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Order;
import model.OrderLine;
import model.Product;

import java.util.Date;
import java.util.List;

@Stateless
@EJB(name="ejb/OrderLineFacade", beanInterface=OrderLineFacade.class, beanName="OrderLineFacade")
public class OrderLineFacade {
	
    @PersistenceContext (unitName = "siw-esame-jsf")
    private EntityManager em;
    
	public OrderLineFacade(EntityManager em){
		this.em=em;
	}
	
	public OrderLineFacade(){

	}
    
	public OrderLine createOrderLine(Float unitPrice, Integer quantity, Order order, Product product) {
		OrderLine orderLine = new OrderLine();
		orderLine.setPrezzoUnitario(unitPrice);
		orderLine.setQuantita(quantity);
		orderLine.setOrder(order);
		orderLine.setProduct(product);
		order = this.em.merge(order);
		this.em.persist(orderLine);
		order.addLineaDiOrdine(orderLine);
		return orderLine;
		
	}
	
	public OrderLine getOrderLine(Long id) {
		TypedQuery<OrderLine> query = this.em.createNamedQuery("OrderLine.findOrderLine", OrderLine.class);
	    query.setParameter("id", id);
	    return query.getSingleResult();
	}
	
	public List<OrderLine> getAllOrderLine() {
        return this.em.createNamedQuery("OrderLine.findAllOrderLine", OrderLine.class).getResultList();
	}

	public void updateOrderLine(OrderLine orderLine) {
        em.merge(orderLine);
	}
	
    private void deleteOrderLine(OrderLine orderLine) {
        em.remove(orderLine);
    }

	public void deleteOrderLine(Long id) {
		OrderLine orderLine = getOrderLine(id);
        deleteOrderLine(orderLine);
	}

}