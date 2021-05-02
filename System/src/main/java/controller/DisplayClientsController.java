package controller;


import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import management.LogisticsCompany;
import management.ClientDatabase;
import view.CompanyPersonalInfoView;
import view.DisplayClientsView;

public class DisplayClientsController {
	//Attributes
	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private DisplayClientsView view;
	private JTextField id = new JTextField();
	private JTextField email = new JTextField();
	
	private ClientDatabase registery = new ClientDatabase();
	Object [] fields = {
			"ID", id,
			"Email", email,  
			
			
	};
	//Constructor
	public DisplayClientsController(LogisticsCompany logisticCompany, Session session) {
		registery.setClients(logisticCompany.getClientDatabase().getClients());
		this.sessionModel = session;
		this.logisticCompany = logisticCompany;
	}
	//Filters the table based on some criteria (id or email)
	public void filter() {
		int response = JOptionPane.showConfirmDialog(null, fields, "Enter relevant search criteria", JOptionPane.OK_CANCEL_OPTION);
		
		String id = this.id.getText();
		String email = this.email.getText();
		
		
		if (response==0) {
			
			registery.setClients(logisticCompany.getClientDatabase().getClients());
			
			if (!id.equals("") && id!=null) {
		        registery.setClients((registery.getClient(Integer.parseInt(id))));
		        SwingUtilities.updateComponentTreeUI(view);

			}
			
			if (!email.equals("") && email!=null) {
		        registery.setClients((registery.getClient(email)));
		        SwingUtilities.updateComponentTreeUI(view);


			}
			
	}}


	//Sends to create client page 
	public void addClient() {
		view.setVisible(false);
		
		CompanyPersonalInfoController companyInfoController = new CompanyPersonalInfoController(this.sessionModel,logisticCompany);

		CompanyPersonalInfoView companyView = new CompanyPersonalInfoView(companyInfoController);
		companyInfoController.setView(companyView);
		companyInfoController.display();
		

	}
	//Setter
	public void setView(DisplayClientsView view) {
		this.view = view;
		this.view.setTableModel(registery);
		this.view.setSession(sessionModel);
	}
	//Refreshes the table so you cancel the filter
	public void refresh() {
        registery.setClients(logisticCompany.getClientDatabase().getClients());
        SwingUtilities.updateComponentTreeUI(view);


	}
	//Displays the display client page
	public void display() {
		view.setVisible(true);
	}
	
}
