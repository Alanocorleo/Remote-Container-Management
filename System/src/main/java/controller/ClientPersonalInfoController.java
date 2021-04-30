package controller;



import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import management.Client;


import management.LogisticsCompany;
import view.ClientPersonalInfoView;



public class ClientPersonalInfoController {


	private Session sessionModel;
	private ClientPersonalInfoView view;
	private Client client;
	private LogisticsCompany company;
	
	public ClientPersonalInfoController(Client client,  Session session,  LogisticsCompany company) {
		this.client = client;
		this.company = company;
		this.sessionModel = session;
	}



	public void setView(ClientPersonalInfoView view) {
		this.view = view;
		//this.view.setTableModel(inventoryModel);
		this.view.setSession(sessionModel);
	}

	public void updateInfo(String FirstName, String LastName, String BirthDate, String Email, String PhoneNumber, String password ) throws Exception {
		boolean birthdayFormatChecker = Pattern.matches("([0-9]{2})\\\\([0-9]{2})\\\\([0-9]{4})", BirthDate);
		boolean EmailFormatChecker = Pattern.matches("^(.+)@(.+)$", Email);
		boolean phoneFormatChecker = Pattern.matches("([0-9]{8,}+)", PhoneNumber);
		boolean nameFormatChecker = Pattern.matches("^[^\\d\\s]+$",FirstName) & Pattern.matches("^[^\\d\\s]+$",LastName);
		boolean passwordFormatChecker = Pattern.matches("^[^\\s]+$",password);
		if (birthdayFormatChecker & EmailFormatChecker & phoneFormatChecker& nameFormatChecker& passwordFormatChecker) {
		String errorMessage = client.updateInfo(FirstName, LastName, BirthDate, Email, PhoneNumber, company.getClientDatabase()).getErrorMessage();
		client.updateInfo(password, company.getClientDatabase()); 
		view.showError(errorMessage);
		}
		else if(!birthdayFormatChecker) {
			view.showError("Incorrect birth date format! It has to follow the following format XX\\XX\\XXXX");
		}else if(!EmailFormatChecker) {
			view.showError("Incorrect email format! It has to follow the following XXXXX@XXXX.XXX");
		}else if(!phoneFormatChecker) {
			view.showError("Incorrect phone format! It has to be composed of at least 8 numbers with no other characters");
		}else if(!nameFormatChecker) {
			view.showError("Incorrect name format! Numbers and white spaces are not allowed");
		}else if(!passwordFormatChecker) {
			view.showError("Incorrect password format! No white space is allowed");
		}else {
			view.showError("ERROR ERROR!! DOWNLOADING VIRUS!!!");
		}
		
		
	}
	public void display() {
		view.setVisible(true);
	}
	public Client getClient() {
		return this.client;
	}
	

}

