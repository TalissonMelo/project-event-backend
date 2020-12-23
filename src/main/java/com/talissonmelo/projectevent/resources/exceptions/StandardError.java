package com.talissonmelo.projectevent.resources.exceptions;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Problema")
@JsonInclude(value = Include.NON_NULL)
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "Erro de validação", position = 2)
	private String message;
	
	@ApiModelProperty(example = "Objeto ou campos que geraram o erro!.", position = 3)
	private List<Field> fields;

	public StandardError(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	@ApiModel("Campos")
	public static class Field {
		
		@ApiModelProperty("nome")
		private String name;
		
		@ApiModelProperty("Campo nome e Obrigatório.")
		private String userMessage;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}
	}
}
