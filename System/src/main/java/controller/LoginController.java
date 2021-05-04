package controller;

import view.LoginView;

/**
 * This class is responsible for restricting the users from access unless they
 * provide the correct credentials.
 */

public class LoginController {
	
	//Attributes
	private ApplicationController application;
	private Session session;
	private LoginView view;
	
	/**
	 * This constructor sets the current application, initiates both a session 
	 * and view.  
	 * @param application
	 */
	//Constructor
	public LoginController(ApplicationController application) {
		this.application = application;
		this.session = new Session();
		this.view = new LoginView(this);	
	}
	
	/**
	 * This method validates user credentials for either the client or logistics 
	 * company by matching the user name and password from the client database.
	 * @param username
	 * @param password
	 */
	public void validateCredentials(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.load(application.getclientdatabase());
		if ((!username.isEmpty()) && user.passwordMatches(password)) {
			session.setUser(user);
			session.setRole(user);
			view.setVisible(false);
			application.mainMenu(session);
		} else {
			view.showError();
		}
	}
	
	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}
}
