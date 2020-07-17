package com.talissonmelo.projectevent.dto.filter;

import java.util.Date;

public class EventFilter {

	private String name;
	private String description;
	private Date initialData;
	private Date finalData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
