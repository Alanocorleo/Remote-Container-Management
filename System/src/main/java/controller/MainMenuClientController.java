package controller;

import management.Client;
import management.LogisticsCompany;
import view.ClientPersonalInfoView;
import view.DisplayJourneysClientView;
import view.DisplayRegisterView;
import view.FriendsMainMenuView;
import view.MainMenuClientView;

public class MainMenuClientController {

		private Client client;
		private LogisticsCompany logisticsCompany;
		private Session sessionModel;
		private MainMenuClientView MainMenuview;
		//private LogisticsCompany company;
		
		public MainMenuClientController(Client client, LogisticsCompany logisticsCompany, Session session) {
			this.client = client;
			this.logisticsCompany = logisticsCompany;
			this.sessionModel = session;
		}

		public void setView(MainMenuClientView MainMenuview) {
			this.MainMenuview  = MainMenuview;
			this.MainMenuview.setSession(sessionModel);
		}

		public void display() {
			MainMenuview.setVisible(true);
		}
		
		public void displayJourneys() {
			this.client.getMyJourneys().setJourneys(this.logisticsCompany.getJourneyDatabase().extract(client.getId()));
			System.out.println(this.logisticsCompany.toString());
			DisplayJourneysClientController displayJourneysController = new DisplayJourneysClientController(this.client, this.sessionModel, this.logisticsCompany);
			DisplayJourneysClientView invView = new DisplayJourneysClientView(displayJourneysController);
			displayJourneysController.setView(invView);
			displayJourneysController.display();
		}
		
		public void register() {
			DisplayRegisterController displayRegisterController = new DisplayRegisterController(this.client, this.logisticsCompany, this.sessionModel);
			DisplayRegisterView invView = new DisplayRegisterView(displayRegisterController);
			displayRegisterController.setView(invView);
			displayRegisterController.display();
		}
		
		public void Friend() {
			FriendsMainMenuController friendsMainMenuController = new FriendsMainMenuController(this.client, this.logisticsCompany,this.sessionModel);

			FriendsMainMenuView clientView = new FriendsMainMenuView(friendsMainMenuController);
			friendsMainMenuController.setView(clientView);
			friendsMainMenuController.display();
		}

		public void updateInfo() {
			ClientPersonalInfoController clientInfoController = new ClientPersonalInfoController(this.client, this.sessionModel, this.logisticsCompany);

			ClientPersonalInfoView clientView = new ClientPersonalInfoView(clientInfoController);
			clientInfoController.setView(clientView);
			clientInfoController.display();
		}
		
		public Client getClient() {
			return client;
		}
		
	}



