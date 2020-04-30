package com.talissonmelo.projectevent.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.talissonmelo.projectevent.domain.enums.UserType;

@JsonInclude(value = Include.NON_NULL)
public class UserDTO {

	private Integer id;

	@NotEmpty(message = "Campo nome e Obrigatório.")
	@Length(min = 8, max = 80, message = "Nome deve ter no mínimo 8 caracteres.")
	private String name;

	@NotEmpty(message = "Email obrigatório.")
	@Email(message = "Email inválido.")
	private String email;

	private Integer userType;

	@NotEmpty(message = "Campo Obrigatório")
	@Length(min = 11, max = 15, message = "Campo deve ter no mínimo 11 caracteres.")
	private String CpfCnpj;

	@NotEmpty(message = "Telefone Obrigatório")
	private String phone;

	@NotEmpty(message = "Senha Obrigatória")
	@Length(min = 6, max = 80, message = "A senha deve ter no mínimo 6 caracteres.")
	private String password;

	public UserDTO() {

	}

	public UserDTO(Integer id, String name, String email, Integer userType, String cpfCnpj, String phone,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.userType = userType;
		CpfCnpj = cpfCnpj;
		this.phone = phone;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
