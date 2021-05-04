package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.Client;
import management.JourneyDatabase;
import management.LogisticsCompany;
import view.DisplayJourneyContainersClientView;
import view.DisplayJourneysClientView;

/**
 * This class is responsible for showing all journeys which the client uses
 * with respect to the registered containers by the client.
 */

public class DisplayJourneysClientController {
	
	private Client client;
	private Session sessionModel;
	private DisplayJourneysClientView view;
	private JourneyDatabase registry = new JourneyDatabase();
	DisplayJourneyContainersCompanyController displayJourneyContainersController;
	private JTextField id = new JTextField();
	private JTextField origin = new JTextField();
	private JTextField destination = new JTextField();
	private JTextField departureDate = new JTextField();
	private JTextField arrivalDate = new JTextField();

	private LogisticsCompany logisticsCompany;
    
	Object [] fields = {
			"ID", id, 
			"Origin", origin,
			"Destination", destination,
			"Departure Date", departureDate,
			"Arrival Date", arrivalDate
	};
	
	/**
	 * This constructor calls both the client and logistics company, sets 
	 * the current session, and sets the current journeys to the registry.
	 * All extracted journeys belong to the client.
	 * @param client
	 * @param logisticsCompany
	 * @param session
	 */
	public DisplayJourneysClientController(Client client, LogisticsCompany logisticsCompany, Session session) {
		registry.setJourneys(client.getMyJourneys().getJourneys());
		this.client = client;
		this.logisticsCompany = logisticsCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method finds the desired journey based on some given input such as
	 * journey-ID, origin, destination, departure date, and arrival date.
	 */
	public void find() {
		int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
		
		String journeyID = this.id.getText();
		String origin = this.origin.getText();
		String destination = this.destination.getText();
		String departureDate = this.departureDate.getText();
		String arrivalDate = this.arrivalDate.getText();
		
		if (response==0) {
			registry.setJourneys(client.getMyJourneys().getJourneys());
			
			if (!journeyID.equals("") && journeyID!=null) {
				registry.setJourneys(client.getMyJourneys().find("journeyID", journeyID));
			}
			
			if (!origin.equals("") && origin!=null) {
				registry.setJourneys(client.getMyJourneys().find("origin", origin));
			}
			
			if (!destination.equals("") && destination!=null) {
				 registry.setJourneys(client.getMyJourneys().find("destination", destination));
			}
			
			if (!departureDate.equals("") && departureDate!=null) {
				 registry.setJourneys(client.getMyJourneys().find("departureDate", departureDate));
			}
			
			if (!arrivalDate.equals("") && arrivalDate!=null) {
				 registry.setJourneys(client.getMyJourneys().find("arrivalDate", arrivalDate));
			}
		}
	}
	
	/**
	 * This method sends the user to the containers window of the selected journey to
	 * show all containers of that journey.
	 * @param selectedRow
	 */
	public void showContainers(int selectedRow) {
		if (selectedRow >= 0) {
			DisplayJourneyContainersClientController displayContainersController = new DisplayJourneyContainersClientController(this.client, this.logisticsCompany, this.sessionModel, selectedRow);
			System.out.println(this.logisticsCompany.toString());

			DisplayJourneyContainersClientView invView = new DisplayJourneyContainersClientView(displayContainersController);
			displayContainersController.setView(invView);
			displayContainersController.display();
		}
	}
	
	/**
	 * This method refreshes the table.
	 */
	public void refresh() {
		registry.setJourneys(client.getMyJourneys().getJourneys());
	}
	
	/**
	 * This method sets the view, table, and view's session.
	 * @param view
	 */
	public void setView(DisplayJourneysClientView invView) {
		this.view = invView;
		this.view.setTableModel(registry);
		this.view.setSession(sessionModel);
	}

	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}
	
}


