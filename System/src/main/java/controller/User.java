package controller;

import management.ClientDatabase;

/**
 * This class is responsible for defining current users.
 */

public final class User {
	
	//Attributes
	private String username;
	private String password;
	
	/**
	 * This method sets the user name.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * This method gets the user name.
	 * @return user name
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * This methods fetches user's password.
	 * @param c
	 */
	public void load(ClientDatabase c) {
		if (this.username.equals("company")) {
			this.password = username;
		} else {
		this.password = c.getPassword(this.username);
		}
	}
	
	/**
	 * This method checks if the given password matches the password from the 
	 * database.
	 * @param password
	 * @return false if the password is null, otherwise return true if the password matches
	 */
	//
	public boolean passwordMatches(String password) {
		if (this.password == null | password == null ) {
			return false;
		} else {
		return (this.password.equals(password));
		}
	}
	
}
