package com.talissonmelo.projectevent.domain.enums;

public enum UserType {
	
	PHYSICS(0, "Pessoa Fisíca"),
	LEGAL(1, "Pessoa Juridíca");
	
	private Integer cod;
	private String text;
	
	
	private UserType(Integer cod, String text) {
		this.cod = cod;
		this.text = text;
	}

	public Integer getCod() {
		return cod;
	}

	public String getText() {
		return text;
	}

	public static UserType toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(UserType user: UserType.values()) {
			if(cod.equals(user.getCod())) {
				return user;
			}
		}
		
		throw new IllegalArgumentException("Id inválido : " + cod);
	}
}
