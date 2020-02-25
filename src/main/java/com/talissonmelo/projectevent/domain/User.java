package com.talissonmelo.projectevent.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.talissonmelo.projectevent.domain.enums.UserType;

@Entity
@Table(name = "Usuario")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private Integer userType;
	private String CpfCnpj;
	private String phone;

	public User() {

	}

	public User(Integer id, String name, String email, UserType userType, String cpfCnpj, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.userType = userType.getCod();
		CpfCnpj = cpfCnpj;
		this.phone = phone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return UserType.toEnum(userType);
	}

	public void setUserType(UserType userType) {
		this.userType = userType.getCod();
	}

	public String getCpfCnpj() {
		return CpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		CpfCnpj = cpfCnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
