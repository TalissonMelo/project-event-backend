package com.talissonmelo.projectevent.domain.enums;

public enum StatusPayment {

	PAID(1, "Pago"), 
	AWAITING_PAYMENT(2, "Aguardando Pagamento"), 
	CANCELED(3, "Cancelado");

	private Integer cod;
	private String text;

	private StatusPayment(Integer cod, String text) {
		this.cod = cod;
		this.text = text;
	}

	public Integer getCod() {
		return cod;
	}

	public String getText() {
		return text;
	}

	public static StatusPayment toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StatusPayment pay : StatusPayment.values()) {
			if (cod.equals(pay.getCod())) {
				return pay;
			}
		}

		throw new IllegalArgumentException("Id inválido : " + cod);
	}
}
