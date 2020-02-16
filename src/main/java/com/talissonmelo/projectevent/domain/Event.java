package com.talissonmelo.projectevent.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Evento")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date initialData;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date finalData;
	private Double price;

	@ManyToMany
	@JoinTable(name = "categoria_evento", 
				joinColumns = @JoinColumn(name = "event_id"), 
				inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();

	public Event() {

	}

	public Event(Integer id, String name, String description, Date initialData, Date finalData, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.initialData = initialData;
		this.finalData = finalData;
		this.price = price;
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

	public List<Category> getCategories() {
		return categories;
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
