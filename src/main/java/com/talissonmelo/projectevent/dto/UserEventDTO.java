package com.talissonmelo.projectevent.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserEventDTO {

	private String user;
	private String email;
	private String phone;
	private String nameEvent;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date initialData;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date finalData;

	private Double price;
	
	public UserEventDTO() {
		
	}

	public UserEventDTO(String user, String email, String phone, String nameEvent, Date initialData, Date finalData,
			Double price) {
		super();
		this.user = user;
		this.email = email;
		this.phone = phone;
		this.nameEvent = nameEvent;
		this.initialData = initialData;
		this.finalData = finalData;
		this.price = price;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
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
}
