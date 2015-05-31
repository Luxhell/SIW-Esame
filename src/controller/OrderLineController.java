package controller;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Order;
import model.OrderLine;
import model.Product;
import facade.OrderLineFacade;

@ManagedBean(name = "orderController")
public class OrderLineController {
	
	
	private Float prezzoUnitario;
	private Integer quantita;
	private Order ordine;
	private Product prodotto;

	private OrderLine orderLine;

	@EJB
	private OrderLineFacade orderLineFacade;
	
	
	public OrderLineController(){
		
	}
	
	public String createOrder(){
		this.orderLine = orderLineFacade.createOrderLine(prezzoUnitario, quantita, ordine, prodotto);
		return "orderLine"; //pagina: orderLine.xhtml
	}

	public Float getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(Float prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Order getOrdine() {
		return ordine;
	}

	public void setOrdine(Order ordine) {
		this.ordine = ordine;
	}

	public Product getProdotto() {
		return prodotto;
	}

	public void setProdotto(Product prodotto) {
		this.prodotto = prodotto;
	}

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public OrderLineFacade getOrderLineFacade() {
		return orderLineFacade;
	}

	public void setOrderLineFacade(OrderLineFacade orderLineFacade) {
		this.orderLineFacade = orderLineFacade;
	}
	
	

}