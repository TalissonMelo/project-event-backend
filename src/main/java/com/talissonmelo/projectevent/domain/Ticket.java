package com.talissonmelo.projectevent.domain;

import java.io.Serializable;

public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;

	private TicketPK id = new TicketPK();

	private Integer amount;
	private Double price;

	public Ticket() {

	}

	public Ticket(Order order, Event event, Integer amount, Double price) {
		super();
		id.setOrder(order);
		id.setEvent(event);
		this.amount = amount;
		this.price = price;
	}

	public Order getOrder() {
		return id.getOrder();
	}

	public Event getEvent() {
		return id.getEvent();
	}

	public TicketPK getId() {
		return id;
	}

	public void setId(TicketPK id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
