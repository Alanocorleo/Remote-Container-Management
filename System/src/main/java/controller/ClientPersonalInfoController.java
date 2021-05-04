package controller;

import java.util.regex.Pattern;

import management.Client;
import management.LogisticsCompany;
import view.ClientPersonalInfoView;

/**
 * This class is responsible for showing client's personal information and 
 * managing it by the client.
 */
 
public class ClientPersonalInfoController {

	private Client client;
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private ClientPersonalInfoView view;
	
	/**
	 * This constructor calls both the client and logistics company, and sets the current session.
	 * @param client
	 * @param logisticsCompany
	 * @param session
	 */
	public ClientPersonalInfoController(Client client, LogisticsCompany logisticsCompany, Session session) {
		this.client = client;
		this.logisticsCompany = logisticsCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method return the client.
	 * @return client
	 */
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * This method checks for right format, and updates client's personal information.
	 * @param FirstName
	 * @param LastName
	 * @param BirthDate
	 * @param Email
	 * @param PhoneNumber
	 * @param password
	 * @throws Exception
	 */
	public void updateInfo(String FirstName, String LastName, String BirthDate, String Email, String PhoneNumber, String password ) throws Exception {
		boolean birthdayFormatChecker = Pattern.matches("^((3[0-1]|2[0-9]|1[0-9]|0[1-9])/(1[0-2]|0[1-9])/([0-9]{4}))$", BirthDate);
		boolean emailFormatChecker = Pattern.matches("^(.+)@(.+)$", Email);
		boolean phoneFormatChecker = Pattern.matches("([0-9]{8,}+)", PhoneNumber);
		boolean nameFormatChecker = Pattern.matches("^[^\\d\\s]+$",FirstName) & Pattern.matches("^[^\\d\\s]+$",LastName);
		boolean passwordFormatChecker = Pattern.matches("^[^\\s]+$",password);
		
		if (birthdayFormatChecker & emailFormatChecker & phoneFormatChecker& nameFormatChecker& passwordFormatChecker) {
		String errorMessage = client.updateInfo(FirstName, LastName, BirthDate, Email, PhoneNumber, logisticsCompany.getClientDatabase()).getErrorMessage();
		client.updateInfo(password, logisticsCompany.getClientDatabase()); 
		view.showError(errorMessage);
		}
		
		else if(!birthdayFormatChecker) {
			view.showError("Incorrect birth date format! It has to follow the following format DD/MM/YYYY");
		
		} else if(!emailFormatChecker) {
			view.showError("Incorrect email format! It has to follow the following XXXXX@XXXX.XXX");
		
		} else if(!phoneFormatChecker) {
			view.showError("Incorrect phone format! It has to be composed of at least 8 numbers with no other characters");
		
		} else if(!nameFormatChecker) {
			view.showError("Incorrect name format! Numbers and white spaces are not allowed");
		
		} else if(!passwordFormatChecker) {
			view.showError("Incorrect password format! No white space is allowed");
		
		} else {
			view.showError("ERROR ERROR!! DOWNLOADING VIRUS!!!");
		}	
	}
	
	/**
	 * This method sets the corresponding view and view's session.
	 * @param view
	 */
	public void setView(ClientPersonalInfoView view) {
		this.view = view;
		this.view.setSession(sessionModel);
	
	}
	
	/**
	 * This method displays the window.
	 */ 
	public void display() {
		view.setVisible(true);
	}

}

