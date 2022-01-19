package model;

public class UserModel {

	public String name, surname, role, email;
	public Integer establishment;
	
	public UserModel() {
	}
	
	public UserModel(String name, String surname, String role, String email, Integer establishment) {
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.email=email;
		this.establishment= establishment;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getRole() {
		return this.role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Integer getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Integer establishment) {
		this.establishment = establishment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
