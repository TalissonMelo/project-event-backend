package com.talissonmelo.projectevent.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.talissonmelo.projectevent.domain.enums.EventType;

@Entity
@Table(name = "Evento")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date initialData;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date finalData;

	private Double price;
	
	private Integer type;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "id.event")
	private Set<Ticket> tickets = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "Participantes")
	private Set<String> participants = new HashSet<>();

	public Event() {

	}

	public Event(Integer id, String name, String description, Date initialData, Date finalData, Double price, EventType type,
			Address address, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.initialData = initialData;
		this.finalData = finalData;
		this.price = price;
		this.type = type.getCod();
		this.address = address;
		this.user = user;
	}

	@JsonIgnore
	public List<Order> getOrders() {
		List<Order> list = new ArrayList<>();
		for (Ticket x : tickets) {
			list.add(x.getOrder());
		}
		return list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getInitialData() {
		return initialData;
	}

	public void setInitialData(Date initialData) {
		this.initialData = initialData;
	}

	public Date getFinalData() {
		return finalData;
	}

	public void setFinalData(Date finalData) {
		this.finalData = finalData;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public EventType getType() {
		return EventType.toEnum(type);
	}

	public void setType(EventType type) {
		this.type = type.getCod();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public Set<String> getParticipants() {
		return participants;
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
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
