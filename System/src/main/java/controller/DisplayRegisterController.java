package controller;

import javax.swing.JOptionPane;

import management.Client;
import management.ContainerDatabase;
import management.JourneyDatabase;
import management.LogisticsCompany;
import view.DisplayJourneysRegisterView;
import view.DisplayRegisterView;

/**
 * This class is responsible for making a request to get the desired journeys
 * by providing relevant input by the client.
 */

public class DisplayRegisterController {
	
    private Client client;
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private DisplayJourneysRegisterController displayJourneysController;
	private DisplayRegisterView view;
	private JourneyDatabase registryJ = new JourneyDatabase();
	private ContainerDatabase registryC = new ContainerDatabase();
	
	/**
	 * This constructor calls both the client and logistics company, and sets 
	 * the current session.
	 * @param client
	 * @param logisticsCompany
	 * @param session
	 */
	public DisplayRegisterController(Client client, LogisticsCompany logisticsCompany, Session session) {
		this.client = client;
 		this.logisticsCompany = logisticsCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method sends the user to the desired journeys window for completing 
	 * registration given some input such as origin, destination, company, content
	 * type, and number of containers. Otherwise show error messages once any of the
	 * input does not satisfy the request for searching.
	 * @param origin
	 * @param destination
	 * @param company
	 * @param contentType
	 * @param numberOfContainers
	 */
	public void request(String origin, String destination, String company, String contentType, String numberOfContainers) {
		String input = "Missing input:\n";
		String info = "";
		registryJ.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
		
		if (!origin.isEmpty() && origin != null) {
			registryJ.setJourneys(registryJ.find("origin", origin));
		}
		else {
			info += "- Origin\n";
		}
		
		if (!destination.isEmpty() && destination != null) {
			 registryJ.setJourneys(registryJ.find("destination", destination));
		} 
		else {
			info += "- Destination\n";
		}
		
		if (company.isEmpty() || company == null) {
			info += "- Company\n";
		}
		
		if (contentType.isEmpty() || contentType == null) {
			info += "- Content Type\n";
		}
		
		if (numberOfContainers.isEmpty() || numberOfContainers == null) {
			info += "- Number of Containers\n";
		}
		
		if (info.isEmpty()) {
			if (registryJ.getJourneys().isEmpty()) {
				view.showError("Your desired journey was not found");
			}
			else {
				int number = Integer.valueOf(numberOfContainers);
				this.registryC.setContainers(logisticsCompany.getContainerDatabase().getContainers());
				this.registryC.setContainers(registryC.extract(number, origin));
				int size = registryC.getContainers().size();
				int reply = JOptionPane.YES_OPTION;
				
				if (size > 0) {
					if (size != number) {
						reply = JOptionPane.showConfirmDialog(null, "There are only " + size + " containers available in " + origin + ". Would you like to proceed with " + size + " containers instead?", "Warning", JOptionPane.YES_NO_OPTION);
					}
					
					if (reply == JOptionPane.YES_OPTION) {
						Object [] inputFields = {origin, contentType, company, number};
						displayJourneysController = new DisplayJourneysRegisterController(this.client, this.logisticsCompany, this.registryJ, this.registryC, inputFields, this.sessionModel);

						DisplayJourneysRegisterView invView = new DisplayJourneysRegisterView(displayJourneysController);
						view.setVisible(false);
						displayJourneysController.setView(invView);
						displayJourneysController.display();
					}
				}
				else {
					view.showError("Unfortunately, there are no more available containers in " + origin);
				}
			}
		}
		else {
			view.showError(input + info);
		}
	}
	
	/**
	 * This method sets the view and view's session.
	 * @param view
	 */
	public void setView(DisplayRegisterView invView) {
		this.view = invView;
		this.view.setSession(sessionModel);
	}

	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}

	
}


