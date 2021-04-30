package controller;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import management.LogisticsCompany;
import management.ClientDatabase;
import view.ClientPersonalInfoView;
import view.CompanyPersonalInfoView;
import view.DisplayClientsView;

public class DisplayClientsController {

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
	public DisplayClientsController(LogisticsCompany logisticCompany, Session session) {
		registery.setClients(logisticCompany.getClientDatabase().getClients());
		this.sessionModel = session;
		this.logisticCompany = logisticCompany;
	}

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


	
	public void addClient() {
		view.setVisible(false);
		
		CompanyPersonalInfoController companyInfoController = new CompanyPersonalInfoController(this.sessionModel,logisticCompany);

		CompanyPersonalInfoView companyView = new CompanyPersonalInfoView(companyInfoController);
		companyInfoController.setView(companyView);
		companyInfoController.display();
		

	}
	
	public void setView(DisplayClientsView view) {
		this.view = view;
		this.view.setTableModel(registery);
		this.view.setSession(sessionModel);
	}
	
	public void refresh() {
        registery.setClients(logisticCompany.getClientDatabase().getClients());
        SwingUtilities.updateComponentTreeUI(view);


	}

	public void display() {
		view.setVisible(true);
	}
	
}
