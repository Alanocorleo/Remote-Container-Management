package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.Client;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.JourneyDatabase;
import management.LogisticsCompany;
import view.DisplayJourneysRegisterView;

public class DisplayJourneysRegisterController {
	
		private Client client;
		private LogisticsCompany logisticCompany;
		private Session sessionModel;
		private DisplayJourneysRegisterView view;
		private Object[] input;
		private JourneyDatabase registryJ = new JourneyDatabase();
		private JourneyDatabase registryJbackup = new JourneyDatabase();
		private ContainerDatabase registryC = new ContainerDatabase();
		private JTextField id = new JTextField();
		private JTextField departureDate = new JTextField();
		private JTextField arrivalDate = new JTextField();
        
		Object [] fields = {
				"ID", id, 
				"Departure date", departureDate,
				"Arrival date", arrivalDate,
		};
		
		public DisplayJourneysRegisterController(Client client, LogisticsCompany logisticCompany, JourneyDatabase registryJ, ContainerDatabase registryC, Object[] input, Session session) {
			this.client = client;
			this.logisticCompany = logisticCompany;
			this.registryJ = registryJ;
			this.registryJbackup.setJourneys(registryJ.getJourneys());;
			this.registryC = registryC;
			this.input = input;
			this.sessionModel = session;
		}
		
		public void find() {
			
			int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
			
			String journeyID = this.id.getText();
			String departureDate = this.departureDate.getText();
			String arrivalDate = this.arrivalDate.getText();
			
			if (response==0) {
				
				registryJ.setJourneys(registryJ.getJourneys());
				
				if (!journeyID.equals("") && journeyID!=null) {
					registryJ.setJourneys(registryJ.find("journeyID", journeyID));
				}
				
				if (!departureDate.equals("") && departureDate!=null) {
					registryJ.setJourneys(registryJ.find("departureDate", departureDate));
				}
				
				if (!arrivalDate.equals("") && arrivalDate!=null) {
					registryJ.setJourneys(registryJ.find("arrivalDate", arrivalDate));
				}
				
			}
		}
				
		public void refresh() {
			registryJ.setJourneys(registryJbackup.getJourneys());
		}
		
		public void select(int selectedRow) throws Exception {
			Journey journey = new Journey();
			journey.setJourneyID(registryJ.getValueAt(selectedRow, 0));
			journey.setOrigin(registryJ.getValueAt(selectedRow, 1));
			journey.setDestination(registryJ.getValueAt(selectedRow, 2));
			journey.setDepartureDate(registryJ.getValueAt(selectedRow, 3));
			journey.setArrivalDate(registryJ.getValueAt(selectedRow, 4));
			
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to register containers to this journey?", "Registration cannot be undone", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				registryC.book(client.getId(), (String) input[0], (String) input[1], (String) input[2], (int) input[3]);
				registryJ.registerTo(journey.getJourneyID(), journey.getOrigin(), journey.getDestination(), registryC.getContainers());
				logisticCompany.getJourneyDatabase().push();
				logisticCompany.getContainerDatabase().push();
				view.showConfirmation("Your container(s) have been successfuly registered to journey " + journey.getJourneyID());
				view.setVisible(false);
			}
			
		}
		
		public void setView(DisplayJourneysRegisterView invView) {
			this.view = invView;
			this.view.setTableModel(registryJ);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
		
	}


