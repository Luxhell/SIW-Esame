package controller;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.Customer;
import model.Order;
import model.OrderLine;
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
	
	@ManagedProperty(value = "#{customerManager}")
	private CustomerManager session;
	
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
		for(Order o: this.orderFacade.findAllOrderNotEvavaded()){
			if((o.getDataChiusuraOrdine()!=null)&&(o.getDataEvasioneOrdine()==null))
				temp.add(o);
		}
		return temp;
	}
	
	public String dettagli (Order order){
		this.order = order;
		return "/portaleAdmin/order.xhtml"; //order.xhtml
	}
	
	public String evadiOrdine(Order order){
		this.orderFacade.evadiOrdine(order);
		return "/portaleAdmin/ordineEvaso.xhtml";
	}
	
	public String cancellaOrdine(Order order){
		for(OrderLine ol: order.getLineeDiOrdine()){
			if(ol != null)
				this.orderFacade.riaggiungiProdottoAlDB(ol.getProdotto(), ol.getQuantita());
		}
		this.orderFacade.deleteOrder(order);
		return "/portaleAdmin/ordineAnnullato.xhtml";
	}
	


	public String confermaAcquisto() {
//		return Integer.toString(this.session.getOrdineCorrente().getLineeDiOrdine().size());
		this.orderFacade.chiudiOrdine(this.session.getOrdineCorrente());
		return "/portaleCustomer/oraPaga.xhtml"; // oraPaga.xhtml
	}
	
	public String visualizzaProdotti() {
		this.session.setOrdineCorrente(this.orderFacade.createOrder(this.session.getCurrent()));
		return "/portaleCustomer/products.xhtml";
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

	public CustomerManager getSession() {
		return session;
	}

	public void setSession(CustomerManager session) {
		this.session = session;
	}
	
	
	
	//FINE METODI GET E SET

	
}
