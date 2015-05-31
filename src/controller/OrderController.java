package controller;


import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Customer;
import model.Order;
import facade.OrderFacade;

@ManagedBean(name = "orderController")
public class OrderController {
	
	
	private java.util.Date dataApertura;
	private java.util.Date dataChiusura;
	private java.util.Date dataEvasione;
	private Customer customer;
	
	private Order order;

	@EJB
	private OrderFacade orderFacade;
	
	
	public OrderController(){
		
	}
	
	public String createOrder(){
		this.order = orderFacade.createOrder(dataApertura, dataChiusura, dataEvasione, customer);
		return "order"; //pagina: order.xhtml
	}
	
	public List<Order> getAll(){
		return this.orderFacade.getAllOrder();
	}

	public java.util.Date getDataApertura() {
		return dataApertura;
	}

	public void setDataApertura(java.util.Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public java.util.Date getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(java.util.Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public java.util.Date getDataEvasione() {
		return dataEvasione;
	}

	public void setDataEvasione(java.util.Date dataEvasione) {
		this.dataEvasione = dataEvasione;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderFacade getOrderFacade() {
		return orderFacade;
	}

	public void setOrderFacade(OrderFacade orderFacade) {
		this.orderFacade = orderFacade;
	}

	//new
	public List<Order> getOrdini2Cliente(){
		List<Order> temp = this.orderFacade.getOrder2Customer(this.customer.getId());
		return(temp!=null ? temp : new LinkedList<Order>());
	}
	
	public Order getOrdineParticolare(Long id_ordine){
		List<Order> ordini_cliente = this.getOrdini2Cliente();
		return this.orderFacade.getOrdine(ordini_cliente, id_ordine);
	}
}
