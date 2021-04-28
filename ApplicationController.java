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
	
	private Client client;
	private LogisticsCompany logisticsCompany;
	private LoginController loginController;
	private MainMenuClientController mainMenuClientController;
	private MainMenuCompanyController mainMenuCompanyController;
	private ClientDatabase clientDataBase;
	
	public ApplicationController() {
		logisticsCompany = new LogisticsCompany();
		clientDataBase = logisticsCompany.getClientDatabase(); 
	}
	
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	
	public void mainMenu(Session session){
		if (session.getRole().equals("Company")) {
			mainMenuCompanyController = new MainMenuCompanyController(this.logisticsCompany, session);

			MainMenuCompanyView mainMenuView = new MainMenuCompanyView(this.mainMenuCompanyController);
			mainMenuCompanyController.setView(mainMenuView);
			mainMenuCompanyController.display();
		}
	
		else {
			this.client = clientDataBase.getClientbyEmail(session.getUsername());
			this.client.getMyJourneys().setJourneys(this.logisticsCompany.getJourneyDatabase().extract(client.getId()));
			mainMenuClientController = new MainMenuClientController(this.client, this.logisticsCompany, session);
			
			MainMenuClientView mainMenuView = new MainMenuClientView(this.mainMenuClientController);
			mainMenuClientController.setView(mainMenuView);
			mainMenuClientController.display();
		}

	}
	
	public ClientDatabase getclientdatabase() {
		return this.clientDataBase;
	}
	
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
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		ApplicationController app = new ApplicationController();
		app.login();
	}
	
}
