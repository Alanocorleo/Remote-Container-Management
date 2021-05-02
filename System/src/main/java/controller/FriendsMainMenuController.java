package controller;

import javax.swing.JOptionPane;
import management.Client;
import management.ClientDatabase;
import management.LogisticsCompany;
import response.ResponseObject;
import view.DisplayJourneysClientView;
import view.FriendsMainMenuView;

public class FriendsMainMenuController {
	//Attributes
	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private FriendsMainMenuView view;
	private ClientDatabase registery = new ClientDatabase() ;
	private Client client;
	//Construcotrs
	public FriendsMainMenuController(Client client, LogisticsCompany logisticCompany, Session session) {
		registery.setClients(client.getMyFriends(logisticCompany.getClientDatabase()));
		this.client = client;
		this.sessionModel = session;
		this.logisticCompany = logisticCompany;
	}

	//adds friends
	public void friendAdding() {
		String newProduct = JOptionPane.showInputDialog("Please insert the email of the client who you want to share infomation with:");
        ResponseObject result = client.addFriend(newProduct, logisticCompany.getClientDatabase());
        view.showError(result.getErrorMessage());
	}
	//sends to friends jounreys page
	public void showJourneys(int selectedRow) {
		if (selectedRow >= 0) {
			//we figure out the friend from the selectedRow
			String email = (String) (registery.getValueAt(selectedRow, 4));
			Client theFriend = registery.getClient(email).get(0);
			DisplayJourneysClientController displayJourneysController = new DisplayJourneysClientController(theFriend, this.sessionModel, this.logisticCompany);
			DisplayJourneysClientView invView = new DisplayJourneysClientView(displayJourneysController);
			displayJourneysController.setView(invView);
			displayJourneysController.display();
			
		}
	}
	//setter
	public void setView(FriendsMainMenuView view) {
		this.view = view;
		this.view.setTableModel(registery);
		this.view.setSession(sessionModel);
	}
	//diplays friends main menu page
	public void display() {
		view.setVisible(true);
	}
	
}
