package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.Client;
import management.ContainerDatabase;
import management.Journey;
import management.LogisticsCompany;
import view.DisplayJourneyContainersClientView;
import view.HistoryView;

/**
 * This class is responsible for showing all registered containers of a
 * selected journey which the client uses by the client.
 */

public class DisplayJourneyContainersClientController {
	
	private Client client;
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private DisplayJourneyContainersClientView view;
	private Journey key;
	private ContainerDatabase registry = new ContainerDatabase();
	private JTextField id = new JTextField();
	private JTextField position = new JTextField();
	private JTextField contentType = new JTextField();
    
	Object [] fields = {
			"ID", id, 
			"Position", position,
			"Content Type", contentType,
	};
	
	/**
	 * This constructor calls both the client and logistics company, sets 
	 * the current session, and extracts containers from the selected journey.
	 * All extracted containers belong to the client.
	 * @param client
	 * @param session
	 * @param selectedRow
	 * @param logisticsCompany
	 */
	public DisplayJourneyContainersClientController(Client client, LogisticsCompany logisticsCompany, Session session, int selectedRow) {
		String journeyID = (String) client.getMyJourneys().getValueAt(selectedRow, 0);
		String origin = (String) client.getMyJourneys().getValueAt(selectedRow, 1);
		String destination = (String) client.getMyJourneys().getValueAt(selectedRow, 2);
	
		this.logisticsCompany = logisticsCompany;
		
		for (Journey key : client.getMyJourneys().getJourneys().keySet()) {
			if (key.getJourneyID().equals(journeyID) && key.getOrigin().equals(origin) &&  key.getDestination().equals(destination)) {
				this.registry.setContainers(client.getMyJourneys().getJourneys().get(key));
				this.client = client;
				this.sessionModel = session;
				this.key = key;
				break;
			}
		}
	}	

	/**
	 * This method finds the desired container based on some given input such as
	 * container-ID, current owner, position, and content type.
	 */
	public void find() {
		int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
		
		String containerID = this.id.getText();
		String position = this.position.getText();
		String contentType = this.contentType.getText();
		
		if (response==0) {
			registry.setContainers(client.getMyJourneys().getJourneys().get(key));
			
			if (!containerID.equals("") && containerID!=null) {
		        registry.setContainers(registry.find("containerID", Integer.parseInt(containerID)));
			}
			
			if (!position.equals("") && position!=null) {
				registry.setContainers(registry.find("position", position));
			}
	        
			if (!contentType.equals("") && contentType!=null) {
				registry.setContainers(registry.find("contentType", contentType));
			}
		}
	}

	/**
	 * This method sends the user to the history book window of the selected 
	 * container to show all information of that container such as date, tmperature,
	 * humidity, pressure, and position.
	 * @param selectedRow
	 */
	public void showHistory(int selectedRow) {
		if (selectedRow >= 0) {
			int id = (int) registry.getValueAt(selectedRow, 0);
			HistoryController historyController;
			historyController = new HistoryController(this.logisticsCompany, this.sessionModel, id);
			HistoryView invView = new HistoryView(historyController);
			historyController.setView(invView);
			historyController.display();
		}
	}
	
	/**
	 * This method refreshes the table.
	 */
	public void refresh() {
		registry.setContainers(client.getMyJourneys().getJourneys().get(key));
	}
	
	/**
	 * This method sets the view, table, and view's session.
	 * @param view
	 */
	public void setView(DisplayJourneyContainersClientView invView) {
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

