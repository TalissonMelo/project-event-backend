package com.talissonmelo.projectevent.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talissonmelo.projectevent.domain.enums.EventType;

public class EventDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo nome e Obrigatório.")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
	private String name;
	
	@NotEmpty(message = "Campo descrição não pode ser vazio.")
	private String description;

	@NotEmpty(message = "Data Inicial Obrogatória.")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo" )
	private Date initialData;

	@NotEmpty(message = "Data Final Obrogatória.")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss",  timezone = "America/Sao_Paulo")
	private Date finalData;

	private Double price;
	
	private Integer type;
	
	public EventDTO() {
		
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
}
