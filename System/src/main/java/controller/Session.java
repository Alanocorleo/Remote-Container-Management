package controller;
import controller.User;

public final class Session {
	//Attributes
	private User user;
	private String role;
	//Constructor
	public Session() {
	}
	//setters and getters
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRole(User user) {
		if (user.getUsername().equals("company")) {
			this.role = "Company";
		} else {
			this.role = "Client";
		}	
	}
	
	public String getUsername() {
		return user.getUsername();
	}
	
	public String getRole() {
		return role;
	}
	
}
