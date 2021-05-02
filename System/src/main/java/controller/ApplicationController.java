package controller;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import management.Client;
import management.ClientDatabase;
import management.LogisticsCompany;

import view.MainMenuClientView;
import view.MainMenuCompanyView;

public class ApplicationController {
	//Attributes
	private Client client;
	private LogisticsCompany logisticsCompany;
	private LoginController loginController;
	private MainMenuClientController mainMenuClientController;
	private MainMenuCompanyController mainMenuCompanyController;
	private ClientDatabase clientDataBase;
	
	//constructor
	public ApplicationController() {
		logisticsCompany = LogisticsCompany.getInstance();
		clientDataBase = logisticsCompany.getClientDatabase(); 
	}
	
	//Sends to the login page 
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	//Sends to the main menu page either the company's or the client's
	public void mainMenu(Session session){
		if (session.getRole().equals("Company")) {
			mainMenuCompanyController = new MainMenuCompanyController(this.logisticsCompany, session);

			MainMenuCompanyView mainMenuView = new MainMenuCompanyView(this.mainMenuCompanyController);
			mainMenuCompanyController.setView(mainMenuView);
			mainMenuCompanyController.display();
		}
	
		else {
			this.client = clientDataBase.getClient(session.getUsername()).get(0);
			mainMenuClientController = new MainMenuClientController(this.client, this.logisticsCompany, session);
			
			MainMenuClientView mainMenuView = new MainMenuClientView(this.mainMenuClientController);
			mainMenuClientController.setView(mainMenuView);
			mainMenuClientController.display();
		}

	}
	
	
	//getter
	public ClientDatabase getclientdatabase() {
		return this.clientDataBase;
	}
	
	//main 
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
