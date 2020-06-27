package com.talissonmelo.projectevent.dto;

import com.talissonmelo.projectevent.domain.enums.StatusPayment;

public class ParticipantsViewDTO {

	private Integer ticketsId;
	private Double price;
	private StatusPayment status;
	private String nameUser;
	private String emailUser;
	private String phoneUser;

	public ParticipantsViewDTO() {

	}

	public ParticipantsViewDTO(Integer ticketsId, Double price, StatusPayment status, String nameUser,
			String emailUser, String phoneUser) {
		this.ticketsId = ticketsId;
		this.price = price;
		this.status = status;
		this.nameUser = nameUser;
		this.emailUser = emailUser;
		this.phoneUser = phoneUser;
	}

	public Integer getTicketsId() {
		return ticketsId;
	}

	public void setTicketsId(Integer ticketsId) {
		this.ticketsId = ticketsId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public StatusPayment getStatus() {
		return status;
	}

	public void setStatus(StatusPayment status) {
		this.status = status;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}
}
