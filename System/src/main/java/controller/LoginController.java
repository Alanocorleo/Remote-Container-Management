package controller;

import controller.User;
import view.LoginView;

public class LoginController {
	//Attributes
	private ApplicationController application;
	private Session session;
	private LoginView view;
	//Constructor
	public LoginController(ApplicationController application) {
		this.application = application;
		this.session = new Session();
		this.view = new LoginView(this);	
	}
	//Checks if password and username match 
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
	//Displays the login view
	public void display() {
		view.setVisible(true);
	}
}
