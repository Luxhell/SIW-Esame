package controller;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import model.Order;
import model.OrderLine;
import model.Product;
import facade.OrderLineFacade;

@ManagedBean(name = "orderLineController")
public class OrderLineController {
	
	
	private Float prezzoUnitario;
	private Integer quantita;
	private Order ordine;
	private Product prodotto;

	private OrderLine orderLine;

	@EJB
	private OrderLineFacade orderLineFacade;
	
	@ManagedProperty(value = "#{customerManager}")
	private CustomerManager session;
	
	public OrderLineController(){
		
	}
	
	public List<OrderLine> visualizzaCarrello() {
		return this.session.getOrdineCorrente().getLineeDiOrdine();
	}
	
	public void eliminaDalCarrello(OrderLine rigaOrdine) {
		if (rigaOrdine.getQuantita() <= 1)
			this.orderLineFacade.deleteOrderLine(rigaOrdine);
		else
			this.orderLineFacade.aggiornaQuantita(rigaOrdine,
					rigaOrdine.getQuantita() - 1);
	}
	
	public String aggiungiAlCarrello(Product product) {
		if (product.isDisponibile()) {		//vedo prima se è disponibile il prodotto
			if (cercaProdotto(this.session.getOrdineCorrente(), product) != null) {
				OrderLine orderLineTemp = cercaProdotto(this.session.getOrdineCorrente(), product);
				if (orderLineTemp.getQuantita() < product.getQuantita())	//se la quantita che ho nel carrello è minore della quantita in magazzino procedo
					this.orderLineFacade.aggiornaQuantita(orderLineTemp, (orderLineTemp.getQuantita()) + 1);
			} else {
				this.session.setOrdineCorrente(this.orderLineFacade.createOrderLine(product.getPrezzo(), 1, this.session.getOrdineCorrente(), product));
			}
		}
		return "/portaleCustomer/products.xhtml";
	}

	private OrderLine cercaProdotto (Order order, Product product){
		for(OrderLine ol :order.getLineeDiOrdine()){
			if(ol.getProdotto().equals(product))
				return ol;
		}
		return null;
	}

	
	
	
	
	
	//INIZIO METODI GET E SET

	public Float getPrezzoUnitario() {
		return this.prezzoUnitario;
	}

	public void setPrezzoUnitario(Float prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public Integer getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Order getOrdine() {
		return this.ordine;
	}

	public void setOrdine(Order ordine) {
		this.ordine = ordine;
	}

	public Product getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}

	public OrderLine getOrderLine() {
		return this.orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public OrderLineFacade getOrderLineFacade() {
		return this.orderLineFacade;
	}

	public void setOrderLineFacade(OrderLineFacade orderLineFacade) {
		this.orderLineFacade = orderLineFacade;
	}

	public CustomerManager getSession() {
		return this.session;
	}

	public void setSession(CustomerManager session) {
		this.session = session;
	}
	
	
	
	//FINE METODI GET E SET

}
