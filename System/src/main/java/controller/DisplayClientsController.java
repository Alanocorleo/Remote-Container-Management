package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import management.LogisticsCompany;
import management.ClientDatabase;
import view.CompanyPersonalInfoView;
import view.DisplayClientsView;

/**
 * This class is responsible for showing all clients available in the database and 
 * managing them by the logistics company.
 */

public class DisplayClientsController {
	
	//Attributes
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private DisplayClientsView view;
	private JTextField id = new JTextField();
	private JTextField email = new JTextField();
	
	private ClientDatabase registery = new ClientDatabase();
	Object [] fields = {
			"ID", id,
			"Email", email,  	
	};
	
	/**
	 * This constructor calls the logistics company, and sets the current session.
	 * @param logisticsCompany
	 * @param session
	 */
	//Constructor
	public DisplayClientsController(LogisticsCompany logisticsCompany, Session session) {
		registery.setClients(logisticsCompany.getClientDatabase().getClients());
		this.sessionModel = session;
		this.logisticsCompany = logisticsCompany;
	}
	
	/**
	 * This method filters the table based on some given input such ID or email.
	 */
	public void filter() {
		int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
		
		String id = this.id.getText();
		String email = this.email.getText();
		
		if (response==0) {
			registery.setClients(logisticsCompany.getClientDatabase().getClients());
			
			if (!id.equals("") && id!=null) {
		        registery.setClients((registery.getClient(Integer.parseInt(id))));
		        SwingUtilities.updateComponentTreeUI(view);
			}
			
			if (!email.equals("") && email!=null) {
		        registery.setClients((registery.getClient(email)));
		        SwingUtilities.updateComponentTreeUI(view);
			}
		}	
	}

	/**
	 * This methods sends the user to adding client window.
	 */
	public void addClient() {
		view.setVisible(false);
		CompanyPersonalInfoController companyInfoController = new CompanyPersonalInfoController(this.logisticsCompany, this.sessionModel);
		CompanyPersonalInfoView companyView = new CompanyPersonalInfoView(companyInfoController);
		companyInfoController.setView(companyView);
		companyInfoController.display();
	}
	
	/**
	 * This method refreshes the table.
	 */
	public void refresh() {
        registery.setClients(logisticsCompany.getClientDatabase().getClients());
        SwingUtilities.updateComponentTreeUI(view);
	}
	
	/**
	 * This method sets the view and view's session.
	 * @param view
	 */
	public void setView(DisplayClientsView view) {
		this.view = view;
		this.view.setTableModel(registery);
		this.view.setSession(sessionModel);
	}
	
	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}
	
}
