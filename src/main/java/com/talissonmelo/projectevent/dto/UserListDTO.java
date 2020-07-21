package com.talissonmelo.projectevent.dto;

public class UserListDTO {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String photo;
	
	public UserListDTO() {
		
	}	

	public UserListDTO(Integer id, String name, String email, String phone, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.photo = photo;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
