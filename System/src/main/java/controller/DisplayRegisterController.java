package controller;

import javax.swing.JOptionPane;

import management.Client;
import management.ContainerDatabase;
import management.JourneyDatabase;
import management.LogisticsCompany;
import view.DisplayJourneysRegisterView;
import view.DisplayRegisterView;

public class DisplayRegisterController {
	
	    private Client client;
		private LogisticsCompany logisticsCompany;
		private Session sessionModel;
		private DisplayJourneysRegisterController displayJourneysController;
		private DisplayRegisterView view;
		private JourneyDatabase registryJ = new JourneyDatabase();
		private ContainerDatabase registryC = new ContainerDatabase();
		
		public DisplayRegisterController(Client client, LogisticsCompany logisticsCompany, Session session) {
			this.client = client;
     		this.logisticsCompany = logisticsCompany;
			this.sessionModel = session;
		}
		
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
		
		public void setView(DisplayRegisterView invView) {
			this.view = invView;
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}

		
	}


