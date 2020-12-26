package com.talissonmelo.projectevent.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.talissonmelo.projectevent.domain.enums.EventType;

public class EventNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer user;
	
	@NotEmpty(message = "Campo nome e Obrigatório.")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres.")
	private String name;

	@NotEmpty(message = "Campo descrição não pode ser vazio.")
	private String description;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date initialData;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date finalData;

	private Double price;

	private Integer type;

	@NotEmpty(message = "Campo bairro não pode ser vazio.")
	private String neighborhooh;
	private String complement;

	@NotEmpty(message = "Campo rua não pode ser vazio.")
	private String street;

	@NotEmpty(message = "Campo número não pode ser vazio.")
	private String number;

	@NotEmpty(message = "Campo CEP não pode ser vazio.")
	private String cep;

	private String participants;

	private Integer cidadeId;

	public EventNewDTO() {

	}
	
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
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

	public String getNeighborhooh() {
		return neighborhooh;
	}

	public void setNeighborhooh(String neighborhooh) {
		this.neighborhooh = neighborhooh;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
}
