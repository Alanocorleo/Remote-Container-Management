package controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import management.ContainerDatabase;
import management.LogisticsCompany;
import view.DisplayContainersView;

/**
 * This class is responsible for showing all containers available in the database
 * and managing them by the logistics company.
 */

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
	
	/**
	 * This constructor calls the logistics company, sets the current session, and
	 * sets all containers from the database to the registry.
	 * @param session
	 * @param company
	 */
	public DisplayContainersController(LogisticsCompany logisticCompany, Session session) {
		registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
		this.sessionModel = session;
		this.logisticCompany = logisticCompany;
	}
	
	/**
	 * This method finds the desired container based on some given input such as
	 * container-ID, current owner, position, content type, company, and availability.
	 */
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
		        registry.setContainers(registry.find("containerID", Integer.parseInt(containerID)));
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
	
	/**
	 * This method registers new containers given location.
	 */
	public void register() {
		String newProduct = JOptionPane.showInputDialog("Please insert the location:");
		if (newProduct != null && !newProduct.equals("")){
			logisticCompany.getContainerDatabase().register(newProduct);
	        registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
		}
	}
	
	/**
	 * This method finds only available containers.
	 */
	public void findAvailable() {
        registry.setContainers(logisticCompany.getContainerDatabase().find(true));
	}

	/**
	 * This method deletes the selected container from the table.
	 * @param selectedRow
	 */
	public void delete(int selectedRow) {
		if (selectedRow >= 0) {
			logisticCompany.getContainerDatabase().getContainers().remove(selectedRow);
			registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
		}
	}
	
	/**
	 * This method refreshes the table.
	 */
	public void refresh() {
        registry.setContainers(logisticCompany.getContainerDatabase().getContainers());
	}
	
	/**
	 * This method saves the changes to the database.
	 * @throws Exception
	 */
	public void save() throws Exception {
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to save changes permanetly?", "Warning", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			logisticCompany.getContainerDatabase().push();
		}
	}
	
	/**
	 * This method sets the view, table, and view's session.
	 * @param view
	 */
	public void setView(DisplayContainersView view) {
		this.view = view;
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

