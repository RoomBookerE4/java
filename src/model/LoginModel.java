package model;

public class LoginModel {
	
	public String name, surname, role;
	
	public LoginModel(String name, String surname, String role) {
		this.name = name;
		this.surname = surname;
		this.role = role;
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
}
