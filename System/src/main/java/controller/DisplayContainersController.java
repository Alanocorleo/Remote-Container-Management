
package controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.ContainerDatabase;
import management.LogisticsCompany;
import view.DisplayContainersView;

public class DisplayContainersController {
	
		private LogisticsCompany logisticCompany;
		private Session sessionModel;
		private DisplayContainersView view;
		private ContainerDatabase registry = new ContainerDatabase();
		private JTextField id = new JTextField();
		private JTextField owner = new JTextField();
		private JTextField position = new JTextField();
		private JTextField contentType = new JTextField();
		private JTextField company = new JTextField();
		private Boolean comboBoxItems[] = {null, Boolean.TRUE, Boolean.FALSE};
        private JComboBox<Object> availability = new JComboBox<Object>(comboBoxItems);
        
		Object [] fields = {
				"ID", id,
				"Current Owner", owner,  
				"Position", position,
				"Content Type", contentType,
				"Company", company,
				"Availability", availability
		};
		
		public DisplayContainersController(LogisticsCompany logisticCompany, Session session) {
			registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
			this.sessionModel = session;
			this.logisticCompany = logisticCompany;
		}
		
		public void find() {
			
			int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
			
			String containerID = this.id.getText();
			String owner = this.owner.getText();
			String position = this.position.getText();
			String contentType = this.contentType.getText();
			String company = this.company.getText();
			Boolean availability = (Boolean) this.availability.getSelectedItem();
			
			if (response==0) {
				
				registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
				
				if (!containerID.equals("") && containerID!=null) {
			        registry.setContainers(registry.find("id", Integer.parseInt(containerID)));
				}
				
				if (!owner.equals("") && owner!=null) {
			        registry.setContainers(registry.find("owner", Integer.parseInt(owner)));
				}
				
				if (!position.equals("") && position!=null) {
					registry.setContainers(registry.find("position", position));
				}
		        
				if (!contentType.equals("") && contentType!=null) {
					registry.setContainers(registry.find("contentType", contentType));
				}
		
				if (!company.equals("") && company!=null) {
					registry.setContainers(registry.find("company", company));
				}
				
				if (availability!=null) {
					registry.setContainers(registry.find(availability));
					}
				
			}
		}
		
		public void register() {
			String newProduct = JOptionPane.showInputDialog("Please insert the location:");
			if(newProduct != null && !newProduct.equals("")){
				logisticCompany.getContainerDatabase().register(newProduct);
		        registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
			}
		}
		
		public void findAvailable() {
	        registry.setContainers(logisticCompany.getContainerDatabase().find(true));
		}

		public void delete(int selectedRow) {
			if (selectedRow >= 0) {
				logisticCompany.getContainerDatabase().getContainers().remove(selectedRow);
				registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
			}
		}
		
		public void refresh() {
	        registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
		}
		
		public void save() throws Exception {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to save changes permanetly?", "Warning", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				logisticCompany.getContainerDatabase().push();
			}
		}
		
		public void setView(DisplayContainersView view) {
			this.view = view;
			this.view.setTableModel(registry);
			this.view.setSession(sessionModel);
		}

		public void display() {
			view.setVisible(true);
		}
	

	}

