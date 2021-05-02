package controller;

import management.Client;
import management.LogisticsCompany;
import view.ClientPersonalInfoView;
import view.DisplayJourneysClientView;
import view.DisplayRegisterView;
import view.FriendsMainMenuView;
import view.MainMenuClientView;

public class MainMenuClientController {
		//Attributes
		private Client client;
		private LogisticsCompany logisticsCompany;
		private Session sessionModel;
		private MainMenuClientView MainMenuview;
		
		//Constructor
		public MainMenuClientController(Client client, LogisticsCompany logisticsCompany, Session session) {
			this.client = client;
			this.logisticsCompany = logisticsCompany;
			this.sessionModel = session;
		}
		//Setter
		public void setView(MainMenuClientView MainMenuview) {
			this.MainMenuview  = MainMenuview;
			this.MainMenuview.setSession(sessionModel);
		}
		//Displays The main menu for clients 
		public void display() {
			MainMenuview.setVisible(true);
		}
		//sends to display journeys for clients 

		public void displayJourneys() {
			this.client.getMyJourneys().setJourneys(this.logisticsCompany.getJourneyDatabase().extract(client.getId()));
			DisplayJourneysClientController displayJourneysController = new DisplayJourneysClientController(this.client, this.sessionModel, this.logisticsCompany);
			DisplayJourneysClientView invView = new DisplayJourneysClientView(displayJourneysController);
			displayJourneysController.setView(invView);
			displayJourneysController.display();
		}
		//sends to register containers for clients 

		public void register() {
			DisplayRegisterController displayRegisterController = new DisplayRegisterController(this.client, this.logisticsCompany, this.sessionModel);
			DisplayRegisterView invView = new DisplayRegisterView(displayRegisterController);
			displayRegisterController.setView(invView);
			displayRegisterController.display();
		}
		//Sends to Friends main menu page 
		public void Friend() {
			FriendsMainMenuController friendsMainMenuController = new FriendsMainMenuController(this.client, this.logisticsCompany,this.sessionModel);

			FriendsMainMenuView clientView = new FriendsMainMenuView(friendsMainMenuController);
			friendsMainMenuController.setView(clientView);
			friendsMainMenuController.display();
		}
		//Sends to page for updating personal information
		public void updateInfo() {
			ClientPersonalInfoController clientInfoController = new ClientPersonalInfoController(this.client, this.sessionModel, this.logisticsCompany);

			ClientPersonalInfoView clientView = new ClientPersonalInfoView(clientInfoController);
			clientInfoController.setView(clientView);
			clientInfoController.display();
		}
		//Getter
		public Client getClient() {
			return client;
		}
		
	}



