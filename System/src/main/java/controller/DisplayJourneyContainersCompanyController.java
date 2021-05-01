package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.LogisticsCompany;
import view.DisplayJourneyContainersCompanyView;

public class DisplayJourneyContainersCompanyController {
	
		private LogisticsCompany logisticCompany;
		private Session sessionModel;
		private DisplayJourneyContainersCompanyView view;
		private Journey key;
		private ContainerDatabase registry = new ContainerDatabase();
		private JTextField ID = new JTextField();
		private JTextField owner = new JTextField();
		private JTextField contentType = new JTextField();
		private JTextField company = new JTextField();
        
		Object [] fields = {
				"ID", ID,
				"Current Owner", owner,  
				"Content type", contentType,
				"Company", company,
		};
		
		public DisplayJourneyContainersCompanyController(LogisticsCompany logisticCompany, Session session, int selectedRow) {
			String journeyID = (String) logisticCompany.getJourneyDatabase().getValueAt(selectedRow, 0);
			String origin = (String) logisticCompany.getJourneyDatabase().getValueAt(selectedRow, 1);
			String destination = (String) logisticCompany.getJourneyDatabase().getValueAt(selectedRow, 2);
			
			for (Journey key : logisticCompany.getJourneyDatabase().getJourneys().keySet()) {
				if (key.getJourneyID().equals(journeyID) && key.getOrigin().equals(origin) &&  key.getDestination().equals(destination)) {
					this.registry.setContainers(logisticCompany.getJourneyDatabase().getJourneys().get(key));
					this.logisticCompany = logisticCompany;
					this.sessionModel = session;
					this.key = key;
					break;
				}
			}
		}
		
		public void find() {
			
			int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
			
			String containerID = this.ID.getText();
			String owner = this.owner.getText();
			String contentType = this.contentType.getText();
			String company = this.company.getText();
			
			if (response==0) {
				
				registry.setContainers(logisticCompany.getJourneyDatabase().getJourneys().get(key));
				
				if (!containerID.equals("") && containerID!=null) {
					registry.setContainers(registry.find("id", Integer.parseInt(containerID)));
				}
				
				if (!owner.equals("") && owner!=null) {
			        registry.setContainers(registry.find("owner", Integer.parseInt(owner)));
				}
		        
				if (!contentType.equals("") && contentType!=null) {
					registry.setContainers(registry.find("contentType", contentType));
				}
		
				if (!company.equals("") && company!=null) {
					registry.setContainers(registry.find("company", company));
				}
				
			}
		}
		
		public void remove(int selectedRow) {
			if (selectedRow >= 0) {
				int containerID = (int) registry.getValueAt(selectedRow, 0);
				logisticCompany.getJourneyDatabase().removeContainer(key.getJourneyID(), containerID);
				for(Container container: logisticCompany.getContainerDatabase().getContainers()) {
					if(container.getContainerID() == containerID) {
						container.setCurrentJourney(null);
//						container.setAvailability(true);
					}
				}
				registry.setContainers(logisticCompany.getJourneyDatabase().getJourneys().get(key));
			}
		}
		
		public void refresh() {
			registry.setContainers(logisticCompany.getJourneyDatabase().getJourneys().get(key));
		}
		
		public void setView(DisplayJourneyContainersCompanyView view) {
			this.view = view;
			this.view.setTableModel(registry);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
	

	}

