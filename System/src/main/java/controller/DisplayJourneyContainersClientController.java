package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.Client;
import management.ContainerDatabase;
import management.Journey;
import management.LogisticsCompany;
import view.DisplayJourneyContainersClientView;
import view.HistoryView;

public class DisplayJourneyContainersClientController {
	
		private Client client;
		private Session sessionModel;
		private DisplayJourneyContainersClientView view;
		private Journey key;
		private ContainerDatabase registry = new ContainerDatabase();
		private JTextField id = new JTextField();
		private JTextField position = new JTextField();
		private JTextField contentType = new JTextField();
		///////parsa
		private LogisticsCompany company;
        
		Object [] fields = {
				"ID", id, 
				"Position", position,
				"Content Type", contentType,
		};
		
		public DisplayJourneyContainersClientController(Client client, Session session, int selectedRow, LogisticsCompany company) {
			String journeyID = (String) client.getMyJourneys().getValueAt(selectedRow, 0);
			String origin = (String) client.getMyJourneys().getValueAt(selectedRow, 1);
			String destination = (String) client.getMyJourneys().getValueAt(selectedRow, 2);
			///////parsa added this
			this.company = company;
			
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
			
		public void find() {
			
			int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
			
			String containerID = this.id.getText();
			String position = this.position.getText();
			String contentType = this.contentType.getText();
			
			if (response==0) {
				
				registry.setContainers(client.getMyContainers());
				
				if (!containerID.equals("") && containerID!=null) {
			        registry.setContainers(registry.find("id", Integer.parseInt(containerID)));
				}
				
				if (!position.equals("") && position!=null) {
					registry.setContainers(registry.find("position", position));
				}
		        
				if (!contentType.equals("") && contentType!=null) {
					registry.setContainers(registry.find("contentType", contentType));
				}
				
			}
		}
		///////////////
		public void showHistory(int selectedRow) {
			if (selectedRow >= 0) {
				
				int id = (int) registry.getValueAt(selectedRow, 0);
				HistoryController historyController;
				System.out.println(this.company.toString());
				
				historyController = new HistoryController(this.company, this.sessionModel, id);

				HistoryView invView = new HistoryView(historyController);
				historyController.setView(invView);
				historyController.display();
				
				
			}
		}
		
		public void refresh() {
			registry.setContainers(client.getMyJourneys().getJourneys().get(key));
		}
		
		public void setView(DisplayJourneyContainersClientView invView) {
			this.view = invView;
			this.view.setTableModel(registry);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
	

	}

