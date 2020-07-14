package com.talissonmelo.projectevent.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UpdatePassword {
	
	@NotBlank(message = "Senha atual informada não identificada.")
	private String password;

	@Length(min = 6, max = 80, message = "A senha deve ter no mínimo 6 caracteres.")
	@NotBlank(message = "Senha informada não identificada.")
	private String newPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
