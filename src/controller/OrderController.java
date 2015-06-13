package controller;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Customer;
import model.Order;
import facade.OrderFacade;

@ManagedBean(name = "orderController")
public class OrderController {
	
	
	private Date dataApertura;
	private Date dataChiusura;
	private Date dataEvasione;
	private Customer customer;
	
	private Order order;

	@EJB
	private OrderFacade orderFacade;
	
	
	public OrderController(){
		
	}
	
	public String createOrder(){
		this.order = orderFacade.createOrder(customer);
		return "/portaleAdmin/order.xhtml"; //pagina: order.xhtml
	}
	
	public List<Order> getAll(){
		return this.orderFacade.getAllOrder();
	}

	public List<Order> getOrdini2Cliente(){
		List<Order> temp = this.orderFacade.getOrder2Customer(this.customer.getId());
		return(temp!=null ? temp : new LinkedList<Order>());
	}
	
	public Order getOrdineParticolare(Long id_ordine){
		List<Order> ordini_cliente = this.getOrdini2Cliente();
		return this.orderFacade.getOrdine(ordini_cliente, id_ordine);
	}
	
	public List<Order> getOrdiniDaEvadere(){
		List<Order> temp = new ArrayList<Order>();
		List<Order> temp2 = this.orderFacade.findAllOrderNotEvavaded();
		for(Order o: temp2){
			if((o.getDataChiusuraOrdine()!=null)&&(o.getDataEvasioneOrdine()==null))
				temp.add(o);
		}
		return temp;
	}
	
	//FORSE POSSO ELIMINARLO (DA PROVARE)
	public String dettagli (Order order){
		this.order = order;
		return "/portaleAdmin/order.xhtml"; //order.xhtml
	}
	
	
	//nuovo
	public String evadiOrdine(Order order){
		this.orderFacade.evadiOrdine(order);
		return "qualcosa";		//	==> da mettere la pagina xhtml di output ;)
	}
	
	//nuovo
	public String cancellaOrdine(Order order){
		this.orderFacade.deleteOrder(order);
		return "staceppa";		// ==> modifica pure qua l' output :D
	}
	
	
	
	
	
	//INIZIO METODI GET E SET
	
	public Date getDataApertura() {
		return this.dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public Date getDataChiusura() {
		return this.dataChiusura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public Date getDataEvasione() {
		return this.dataEvasione;
	}

	public void setDataEvasione(Date dataEvasione) {
		this.dataEvasione = dataEvasione;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderFacade getOrderFacade() {
		return this.orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}
	
	//FINE METODI GET E SET

	
}
