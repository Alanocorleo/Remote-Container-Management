package controller;

import javax.swing.JOptionPane;
import management.Client;
import management.ClientDatabase;
import management.LogisticsCompany;
import response.ResponseObject;
import view.DisplayJourneysClientView;
import view.FriendsMainMenuView;

/**
 * This class is responsible for showing client's friends and managing them by the
 * client. 
 */
public class FriendsMainMenuController {
	
	//Attributes
	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private FriendsMainMenuView view;
	private ClientDatabase registery = new ClientDatabase() ;
	private Client client;
	
	/**
	 * This constructor calls both the client and logistics company, and sets 
	 * the current session.
	 * @param client
	 * @param logisticsCompany
	 * @param session
	 */
	//Construcotrs
	public FriendsMainMenuController(Client client, LogisticsCompany logisticCompany, Session session) {
		registery.setClients(client.getMyFriends(logisticCompany.getClientDatabase()));
		this.client = client;
		this.logisticCompany = logisticCompany;
		this.sessionModel = session;
	}

	/**
	 * This method allows the client to share information with another given the email
	 * of the other client.
	 */
	public void friendAdding() {
		String newProduct = JOptionPane.showInputDialog("Please insert the email of the client who you want to share infomation with:");
        ResponseObject result = client.addFriend(newProduct, logisticCompany.getClientDatabase());
        view.showError(result.getErrorMessage());
	}
	
	/**
	 * This method sends the user to the journeys window of the selected friend.
	 * @param selectedRow
	 */
	public void showJourneys(int selectedRow) {
		if (selectedRow >= 0) {
			//we figure out the friend from the selectedRow
			String email = (String) (registery.getValueAt(selectedRow, 4));
			Client theFriend = registery.getClient(email).get(0);
			theFriend.getMyJourneys().setJourneys(this.logisticCompany.getJourneyDatabase().extract(theFriend.getId()));
			
			DisplayJourneysClientController displayJourneysController = new DisplayJourneysClientController(theFriend, this.logisticCompany, this.sessionModel);
			DisplayJourneysClientView invView = new DisplayJourneysClientView(displayJourneysController);
			displayJourneysController.setView(invView);
			displayJourneysController.display();
		}
	}
	
	/**
	 * This method sets the view, table, and view's session.
	 * @param view
	 */
	//setter
	public void setView(FriendsMainMenuView view) {
		this.view = view;
		this.view.setTableModel(registery);
		this.view.setSession(sessionModel);
	}
	
	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}
	
}
