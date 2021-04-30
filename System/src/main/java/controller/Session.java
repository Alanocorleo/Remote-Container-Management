package controller;
import management.User;

public final class Session {

	private User user;
	private String role;
	
	public Session() {
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setRole(User user) {
		System.out.println(user.getUsername().equals("company"));
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
