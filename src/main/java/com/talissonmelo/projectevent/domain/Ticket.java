package com.talissonmelo.projectevent.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ingresso")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
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

	public Double getSubTotal() {
		return price * amount;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public Event getEvent() {
		return id.getEvent();
	}

	public void setEvent(Event event) {
		id.setEvent(event);
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

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Organizador do Evento : " );
		builder.append(getEvent().getUser().getName());
		builder.append("\nContato para Pagamento do Ingresso : " );
		builder.append(getEvent().getUser().getEmail());
		builder.append("\nContato para Pagamento do Ingresso : " );
		builder.append(getEvent().getUser().getPhone());
		builder.append("\nEvento : " );
		builder.append(getEvent().getName());
		builder.append("\nInicio do Evento : ");
		builder.append(sdf.format(getEvent().getInitialData()));
		builder.append("\nTermino do Evento : ");
		builder.append(sdf.format(getEvent().getFinalData()));
		builder.append("\nEndereço\nRua: ");
		builder.append(getEvent().getAddress().getStreet());
		builder.append(", número : ");
		builder.append(getEvent().getAddress().getNumber());
		builder.append(", CEP : ");
		builder.append(getEvent().getAddress().getCep());
		builder.append(", Bairro : ");
		builder.append(getEvent().getAddress().getNeighborhooh());
		builder.append(", Cidade : ");
		builder.append(getEvent().getAddress().getCity().getName());
		builder.append(", Estado : ");
		builder.append(getEvent().getAddress().getCity().getState().getName());
		builder.append("\nQuantidade : ");
		builder.append(getAmount());
		builder.append(", Preço : ");
		builder.append(nf.format(getPrice()));
		builder.append(", Total R$: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
