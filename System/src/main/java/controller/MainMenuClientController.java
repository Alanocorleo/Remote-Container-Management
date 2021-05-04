package controller;

import management.Client;
import management.LogisticsCompany;
import view.ClientPersonalInfoView;
import view.DisplayJourneysClientView;
import view.DisplayRegisterView;
import view.FriendsMainMenuView;
import view.MainMenuClientView;

/**
 * This class is responsible for showing client's main menu interface window once
 * the client is logged in.
 */

public class MainMenuClientController {
	
	//Attributes
	private Client client;
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private MainMenuClientView MainMenuview;
	
	/**
	 * This constructor calls both the client and logistics company, and sets 
	 * the current session.
	 * @param client
	 * @param logisticsCompany
	 * @param session
	 */
	//Constructor
	public MainMenuClientController(Client client, LogisticsCompany logisticsCompany, Session session) {
		this.client = client;
		this.logisticsCompany = logisticsCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method returns the client
	 * @return client
	 */
	public Client getClient() {
		return client;
	}
	
	//sends to display journeys for clients 

	/**
	 * This method sends the user to the journeys window to show all the journeys
	 * the user has registered containers to.
	 */
	public void displayJourneys() {
		this.client.getMyJourneys().setJourneys(this.logisticsCompany.getJourneyDatabase().extract(client.getId()));
		DisplayJourneysClientController displayJourneysController = new DisplayJourneysClientController(this.client, this.logisticsCompany, this.sessionModel);
		DisplayJourneysClientView invView = new DisplayJourneysClientView(displayJourneysController);
		displayJourneysController.setView(invView);
		displayJourneysController.display();
	}
	
	/**
	 * This method sends the user to the register window to allow the user to make a
	 * request, and eventually book and register containers to the desired journeys.
	 */
	public void register() {
		DisplayRegisterController displayRegisterController = new DisplayRegisterController(this.client, this.logisticsCompany, this.sessionModel);
		DisplayRegisterView invView = new DisplayRegisterView(displayRegisterController);
		displayRegisterController.setView(invView);
		displayRegisterController.display();
	}
	
	/**
	 * This method sends the user to the friends window to see all friends who shared
	 * their information with client, and to share information with others.
	 */
	public void friend() {
		FriendsMainMenuController friendsMainMenuController = new FriendsMainMenuController(this.client, this.logisticsCompany, this.sessionModel);

		FriendsMainMenuView clientView = new FriendsMainMenuView(friendsMainMenuController);
		friendsMainMenuController.setView(clientView);
		friendsMainMenuController.display();
	}
	
	/**
	 * This method sends the user to the update information window to update personal
	 * information.
	 */
	public void updateInfo() {
		ClientPersonalInfoController clientInfoController = new ClientPersonalInfoController(this.client, this.logisticsCompany, this.sessionModel);

		ClientPersonalInfoView clientView = new ClientPersonalInfoView(clientInfoController);
		clientInfoController.setView(clientView);
		clientInfoController.display();
	}
	
	/**
	 * This method sets the view and view's session.
	 * @param MainMenuview
	 */
	public void setView(MainMenuClientView MainMenuview) {
		this.MainMenuview  = MainMenuview;
		this.MainMenuview.setSession(sessionModel);
	}
	
	/**
	 * This method displays the window.
	 */
	public void display() {
		MainMenuview.setVisible(true);
	}
	
}



