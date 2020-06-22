package com.talissonmelo.projectevent.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserEventDTO {

	private String userName;
	private String userEmail;
	private String userPhone;
	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date initialData;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date finalData;

	private Double price;

	public UserEventDTO() {

	}

	public UserEventDTO(String userName, String userEmail, String userPhone, String name, Date initialData,
			Date finalData, Double price) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.name = name;
		this.initialData = initialData;
		this.finalData = finalData;
		this.price = price;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
