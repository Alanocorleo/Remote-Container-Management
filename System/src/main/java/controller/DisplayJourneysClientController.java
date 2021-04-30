package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.Client;
import management.JourneyDatabase;
import management.LogisticsCompany;
import view.DisplayJourneyContainersClientView;
import view.DisplayJourneysClientView;

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
		////////////
		private LogisticsCompany company;
        
		Object [] fields = {
				"ID", id, 
				"Origin", origin,
				"Destination", destination,
				"Departure Date", departureDate,
				"Arrival Date", arrivalDate
		};
		
		public DisplayJourneysClientController(Client client, Session session, LogisticsCompany company) {
			registry.setJourneys(client.getMyJourneys().getJourneys());
			this.client = client;
			this.sessionModel = session;
			this.company = company;
		}
		
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
		
		public void showContainers(int selectedRow) {
			if (selectedRow >= 0) {
				DisplayJourneyContainersClientController displayContainersController = new DisplayJourneyContainersClientController(this.client, this.sessionModel, selectedRow, this.company);
				System.out.println(this.company.toString());

				DisplayJourneyContainersClientView invView = new DisplayJourneyContainersClientView(displayContainersController);
				displayContainersController.setView(invView);
				displayContainersController.display();
			}
		}
		
		public void refresh() {
			registry.setJourneys(client.getMyJourneys().getJourneys());
		}
		
		
		public void setView(DisplayJourneysClientView invView) {
			this.view = invView;
			this.view.setTableModel(registry);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
		
	}


