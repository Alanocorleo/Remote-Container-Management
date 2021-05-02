package controller;

import management.ClientDatabase;

public final class User {
	//Attributes
	private String username;
	private String password;
	//setters and getters
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	//Fetches the username's password
	public void load(ClientDatabase c) {
		
		if (this.username.equals("company")) {
			this.password = username;
			
		} 
		
		else {

		this.password = c.getPassword(this.username);
		}
		
	}
	//Checks if the given password matches the password from the database
	public boolean passwordMatches(String password) {
		if (this.password == null | password == null ) {
			return false;
		}else {
		return (this.password.equals(password));
		}
	}
	
}
