package controller;

/**
 * This class is responsible for specifying current sessions.
 */

public final class Session {
	
	//Attributes
	private User user;
	private String role;
	
	//Constructor
	public Session() {
	}
	
	/**
	 * This method sets the user.
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * This method sets the role.
	 * @param user
	 */
	public void setRole(User user) {
		if (user.getUsername().equals("company")) {
			this.role = "Company";
		} else {
			this.role = "Client";
		}	
	}
	
	/**
	 * This method returns the user name.
	 * @return user name
	 */
	public String getUsername() {
		return user.getUsername();
	}
	
	/**
	 * This method returns the role.
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	
}
