package com.talissonmelo.projectevent.domain.enums;

public enum EventType {
	
	PUBLIC(0, "Publico"),
	PRIVATE(1, "Privado");
	
	private Integer cod;
	private String text;
	
	private EventType(Integer cod, String text) {
		this.cod = cod;
		this.text = text;
	}

	public Integer getCod() {
		return cod;
	}

	public String getText() {
		return text;
	}
	
	public static EventType toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(EventType event : EventType.values()){
			if(cod.equals(event.getCod())) {
				return event;
			}
			
		}
		
		throw new IllegalArgumentException("Enum não encontrado : " + cod);
		
	}

}
