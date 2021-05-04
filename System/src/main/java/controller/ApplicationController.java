package controller;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import management.Client;
import management.ClientDatabase;
import management.LogisticsCompany;

import view.MainMenuClientView;
import view.MainMenuCompanyView;

/**
 * This class is responsible for initiating the application.
 */

public class ApplicationController {
	
	// Attributes
	private Client client;
	private LogisticsCompany logisticsCompany;
	private LoginController loginController;
	private MainMenuClientController mainMenuClientController;
	private MainMenuCompanyController mainMenuCompanyController;
	private ClientDatabase clientDatabase;
	
	/**
	 * This constructor calls the logistics company, and sets the client database.
	 */
	// Constructor
	public ApplicationController() {
		logisticsCompany = LogisticsCompany.getInstance();
		clientDatabase = logisticsCompany.getClientDatabase(); 
	}
	
	/**
	 * This method sends the user to the login page. 
	 */
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}

	/**
	 * This method sends the users, either the client, or the logistics company, to 
	 * their corresponding main menu windows.
	 * @param session
	 */
	public void mainMenu(Session session){
		if (session.getRole().equals("Company")) {
			mainMenuCompanyController = new MainMenuCompanyController(this.logisticsCompany, session);

			MainMenuCompanyView mainMenuView = new MainMenuCompanyView(this.mainMenuCompanyController);
			mainMenuCompanyController.setView(mainMenuView);
			mainMenuCompanyController.display();
		}
	
		else {
			this.client = clientDatabase.getClient(session.getUsername()).get(0);
			mainMenuClientController = new MainMenuClientController(this.client, this.logisticsCompany, session);
			
			MainMenuClientView mainMenuView = new MainMenuClientView(this.mainMenuClientController);
			mainMenuClientController.setView(mainMenuView);
			mainMenuClientController.display();
		}

	}
	
	/**
	 * This method return client database.
	 * @return client database
	 */
	public ClientDatabase getclientdatabase() {
		return this.clientDatabase;
	}
	
	/**
	 * This main method starts the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		        	UIManager.setLookAndFeel(info.getClassName());
		        	UIManager.put("control", new Color(255,255,255));
		        	UIManager.put("nimbusBlueGrey", new Color(115,164,209));
		            break;
		        }
		    }
		} catch (Exception e) {
		}
		ApplicationController app = new ApplicationController();
		app.login();
	}
	
}
