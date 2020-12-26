package com.talissonmelo.projectevent.dto;

import java.io.Serializable;

import com.talissonmelo.projectevent.domain.enums.StatusPayment;

public class UpdateStatusDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	
	public UpdateStatusDTO() {
		
	}

	public UpdateStatusDTO(StatusPayment status) {
		super();
		this.status = status.getCod();
	}

	public StatusPayment getStatus() {
		return StatusPayment.toEnum(status);
	}

	public void setStatus(StatusPayment status) {
		this.status = status.getCod();
	}
}
