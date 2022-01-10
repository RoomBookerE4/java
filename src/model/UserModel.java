package model;

public class UserModel {

	public String name, surname, role, email;
	public int establishment;
	
	public UserModel(String name, String surname, String role, String email, int establishment) {
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

	

	public int getEstablishment() {
		return establishment;
	}

	public void setEstablishment(int establishment) {
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
