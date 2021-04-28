package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.LogisticsCompany;
import management.Container;
import management.Journey;
import management.JourneyDatabase;
import view.DisplayJourneyContainersView;
import view.DisplayJourneysView;

public class DisplayJourneysController {
	
		private LogisticsCompany logisticCompany;
		private Session sessionModel;
		private DisplayJourneysView view;
		private JourneyDatabase registry = new JourneyDatabase();
		private DisplayJourneyContainersController displayJourneyContainersController;
		private JTextField id = new JTextField();
		private JTextField origin = new JTextField();
		private JTextField destination = new JTextField();
		private JTextField departureDate = new JTextField();
        
		Object [] fields = {
				"ID", id, 
				"Origin", origin,
				"Destination", destination
		};
		
		Object [] fields2 = {
				"Origin", origin,
				"Destination", destination,
				"Departure date", departureDate
		};
		
		public DisplayJourneysController(LogisticsCompany logisticCompany, Session session) {
			registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
			this.logisticCompany = logisticCompany;
			this.sessionModel = session;
		}
		
		public void find() {
			
			int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
			
			String journeyID = this.id.getText();
			String origin = this.origin.getText();
			String destination = this.destination.getText();
			
			if (response==0) {
				
				registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
				
				if (!journeyID.equals("") && journeyID!=null) {
					registry.setJourneys(logisticCompany.getJourneyDatabase().find("journeyID", journeyID));
				}
				
				if (!origin.equals("") && origin!=null) {
					registry.setJourneys(logisticCompany.getJourneyDatabase().find("origin", origin));
				}
				
				if (!destination.equals("") && destination!=null) {
					 registry.setJourneys(logisticCompany.getJourneyDatabase().find("destination", destination));
				}
				
			}
		}
		
		public void showContainers(int selectedRow) {
			if (selectedRow >= 0) {
				displayJourneyContainersController = new DisplayJourneyContainersController(this.logisticCompany, this.sessionModel, selectedRow);

				DisplayJourneyContainersView invView = new DisplayJourneyContainersView(displayJourneyContainersController);
				displayJourneyContainersController.setView(invView);
				displayJourneyContainersController.display();
			}
		}
		
		public void updatePosition(int selectedRow) {
			if (selectedRow >= 0) {
				String journeyID = (String) registry.getValueAt(selectedRow, 0);
				String position = JOptionPane.showInputDialog("Enter updated position:");
				
				if(position != null && position.equals("")) {
					view.showError2();
				}
				
				else if(position != null && !position.equals("")) {
					logisticCompany.getJourneyDatabase().updatePosition(journeyID, position);
					logisticCompany.getContainerDatabase().updatePosition(journeyID, position);
					registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
				}
			}
		}
		
		public void arrived(int selectedRow) {
			if (selectedRow >= 0) {
				String journeyID = (String) registry.getValueAt(selectedRow, 0);
			
				logisticCompany.getJourneyDatabase().markArrived(journeyID);
				logisticCompany.getContainerDatabase().markArrived(journeyID);
				registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
				
			}
		}
		
		public void add() {
			
			int response = JOptionPane.showConfirmDialog(null, fields2, "Enter relevant details", JOptionPane.OK_CANCEL_OPTION);
			
			String origin = this.origin.getText();
			String destination = this.destination.getText();
			String departureDate = this.departureDate.getText();
			Journey journey = new Journey();
			journey.setJourneyDatabase(logisticCompany.getJourneyDatabase());
			
			if (response==0) {
				
				if(!(origin.equals("") || origin==null || destination.equals("") || destination==null)) {
					
					if (!origin.equals("") && origin!=null) {
						journey.setOrigin(origin);
					}
					
					if (!destination.equals("") && destination!=null) {
						 journey.setDestination(destination);
					}
					
					if (!departureDate.equals("") && departureDate!=null) {
						journey.setDepartureDate(departureDate);
					}
					
					else {
						journey.setDepartureDate("null");
					}
					
					journey.setArrivalDate("null");
					journey.setJourneyID(journey.createJourneyID());
					logisticCompany.getJourneyDatabase().create(journey);
					
				}
				
				else {
					view.showError();
				}
		
			}
			
			registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
			
		}
		
		public void delete(int selectedRow) {
			if (selectedRow >= 0) {
				String journeyID = (String) registry.getValueAt(selectedRow, 0);
				logisticCompany.getJourneyDatabase().del(journeyID);
				for(Container container : logisticCompany.getContainerDatabase().find("journey", journeyID)) {
					container.setAvailability(true);
					container.setOwner(0);
					container.setCurrentJourney("");
					container.setCompany("");
					container.setContentType("");
				}
				registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
				
			}
		}
		
		public void refresh() {
			registry.setJourneys(logisticCompany.getJourneyDatabase().getJourneys());
		}
		
		public void save() throws Exception {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to save changes permanetly?", "Warning", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				logisticCompany.getJourneyDatabase().push();
				logisticCompany.getContainerDatabase().push();
			}
		}
		
		public void setView(DisplayJourneysView invView) {
			this.view = invView;
			this.view.setTableModel(registry);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
		
	}


