package controller;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.LogisticsCompany;
import management.Container;
import management.Journey;
import management.JourneyDatabase;
import view.DisplayJourneyContainersCompanyView;
import view.DisplayJourneysCompanyView;

public class DisplayJourneysCompanyController {
	
		private LogisticsCompany logisticsCompany;
		private Session sessionModel;
		private DisplayJourneysCompanyView view;
		private JourneyDatabase registry = new JourneyDatabase();
		private DisplayJourneyContainersCompanyController displayJourneyContainersController;
		private JTextField id = new JTextField();
		private JTextField origin = new JTextField();
		private JTextField destination = new JTextField();
		private JTextField departureDate = new JTextField();
		private JTextField arrivalDate = new JTextField();
        
		Object [] fields = {
				"ID", id, 
				"Origin", origin,
				"Destination", destination,
				"Departure Date", departureDate,
				"Arrival Date", arrivalDate
		};
		
		Object [] fields2 = {
				"Origin", origin,
				"Destination", destination,
				"Departure date", departureDate,
				"Arrival Date", arrivalDate
		};
		
		public DisplayJourneysCompanyController(LogisticsCompany logisticsCompany, Session session) {
			registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
			this.logisticsCompany = logisticsCompany;
			this.sessionModel = session;
		}
		
		public void find() {
			
			int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
			
			String journeyID = this.id.getText();
			String origin = this.origin.getText();
			String destination = this.destination.getText();
			String departureDate = this.departureDate.getText();
			String arrivalDate = this.arrivalDate.getText();
			
			if (response==0) {
				
				registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
				
				if (!journeyID.equals("") && journeyID!=null) {
					registry.setJourneys(logisticsCompany.getJourneyDatabase().find("journeyID", journeyID));
				}
				
				if (!origin.equals("") && origin!=null) {
					registry.setJourneys(logisticsCompany.getJourneyDatabase().find("origin", origin));
				}
				
				if (!destination.equals("") && destination!=null) {
					 registry.setJourneys(logisticsCompany.getJourneyDatabase().find("destination", destination));
				}
				
				if (!departureDate.equals("") && departureDate!=null) {
					 registry.setJourneys(logisticsCompany.getJourneyDatabase().find("departureDate", departureDate));
				}
				
				if (!arrivalDate.equals("") && arrivalDate!=null) {
					 registry.setJourneys(logisticsCompany.getJourneyDatabase().find("arrivalDate", arrivalDate));
				}
				
			}
		}
		
		public void showContainers(int selectedRow) {
			if (selectedRow >= 0) {
				displayJourneyContainersController = new DisplayJourneyContainersCompanyController(this.logisticsCompany, this.sessionModel, selectedRow);

				DisplayJourneyContainersCompanyView invView = new DisplayJourneyContainersCompanyView(displayJourneyContainersController);
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
					logisticsCompany.getJourneyDatabase().updatePosition(journeyID, position);
					logisticsCompany.getContainerDatabase().updatePosition(journeyID, position);
					registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
				}
			}
		}
		
		public void setDeparture(int selectedRow) {
			if (selectedRow >= 0) {
				String journeyID = (String) registry.getValueAt(selectedRow, 0);
				String date = JOptionPane.showInputDialog("Enter departure date:");
				
				if(date!=null) {
					boolean dateFormatChecker = Pattern.matches("^((3[0-1]|2[0-9]|1[0-9]|[1-9])/(1[0-2]|0[1-9])/([0-9]{4}))$", date);
					if(dateFormatChecker) {
						logisticsCompany.getJourneyDatabase().setDeparture(journeyID, date);
					}
					
					else {
						view.showError3();
					}
				}
				
				registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
				
			}
		}
		
		public void arrived(int selectedRow) {
			if (selectedRow >= 0) {
				String journeyID = (String) registry.getValueAt(selectedRow, 0);
			
				logisticsCompany.getJourneyDatabase().markArrived(journeyID);
				logisticsCompany.getContainerDatabase().markArrived(journeyID);
				registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
				
			}
		}
		
		public void add() {
			
			int response = JOptionPane.showConfirmDialog(null, fields2, "Enter relevant details", JOptionPane.OK_CANCEL_OPTION);
			
			String origin = this.origin.getText();
			String destination = this.destination.getText();
			String departureDate = this.departureDate.getText();
			String arrivalDate = this.arrivalDate.getText();
			
			Journey journey = new Journey();
			journey.setJourneyDatabase(logisticsCompany.getJourneyDatabase());
			
			if (response==0) {
				
				if(!(origin.equals("") || origin==null || destination.equals("") || destination==null)) {
					
					journey.setOrigin(origin);
					journey.setDestination(destination);
					
					if (!departureDate.equals("") && departureDate!=null && Pattern.matches("^((3[0-1]|2[0-9]|1[0-9]|[1-9])/(1[0-2]|0[1-9])/([0-9]{4}))$", departureDate)) {
						journey.setDepartureDate(departureDate);
						
						if (!arrivalDate.equals("") && arrivalDate!=null && Pattern.matches("^((3[0-1]|2[0-9]|1[0-9]|[1-9])/(1[0-2]|0[1-9])/([0-9]{4}))$", arrivalDate)) {
							journey.setArrivalDate(arrivalDate);
							journey.setJourneyID(journey.createJourneyID());
							logisticsCompany.getJourneyDatabase().create(journey);
						}
						else {
							view.showError3();
						}
				
					} 
					else {
						view.showError3();
					}
					
				}
				else {
					view.showError();
				}
		
			}
			
			registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
			
		}
		
		public void delete(int selectedRow) {
			if (selectedRow >= 0) {
				String journeyID = (String) registry.getValueAt(selectedRow, 0);
				logisticsCompany.getJourneyDatabase().complete(journeyID);
				for (Container container : logisticsCompany.getContainerDatabase().find("journeyID", journeyID)) {
					container.setAvailability(true);
					container.setOwner(0);
					container.setCurrentJourney("");
					container.setCompany("");
					container.setContentType("");
				}
				registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
			}
		}
		
		public void refresh() {
			registry.setJourneys(logisticsCompany.getJourneyDatabase().getJourneys());
		}
		
		public void save() throws Exception {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to save changes permanetly?", "Warning", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				logisticsCompany.getJourneyDatabase().push();
				logisticsCompany.getContainerDatabase().push();
			}
		}
		
		public void setView(DisplayJourneysCompanyView invView) {
			this.view = invView;
			this.view.setTableModel(registry);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
		
	}


