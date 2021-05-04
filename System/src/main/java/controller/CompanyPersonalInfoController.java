package controller;

import java.util.regex.*;  
import management.Client;

import management.LogisticsCompany;
import response.ResponseObject;
import view.CompanyPersonalInfoView;
import view.DisplayClientsView;

/**
 * This class is responsible for creating a client by providing client's personal 
 * information by the logistics company.
 */

public class CompanyPersonalInfoController {
	
	//Attributes
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private CompanyPersonalInfoView view;
	
	/**
	 * This constructor calls the logistics company, and sets the current session.
	 * @param logisticsCompany
	 * @param session
	 */
	//Constructor
	public CompanyPersonalInfoController(LogisticsCompany logisticsCompany, Session session) {
		this.logisticsCompany = logisticsCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method is responsible for creating a client given the correct information,
	 * and show error messages otherwise.
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param email
	 * @param phoneNumber
	 * @param password
	 * @throws Exception
	 */
	public void createInfo(String firstName, String lastName, String birthDate, String email, String phoneNumber, String password ) throws Exception {
		boolean birthdayFormatChecker = Pattern.matches("^((3[0-1]|2[0-9]|1[0-9]|0[1-9])/(1[0-2]|0[1-9])/([0-9]{4}))$", birthDate);
		boolean EmailFormatChecker = Pattern.matches("^(.+)@(.+)$", email);
		boolean phoneFormatChecker = Pattern.matches("([0-9]{8,}+)", phoneNumber);
		boolean nameFormatChecker = Pattern.matches("^[^\\d\\s]+$",firstName) & Pattern.matches("^[^\\d\\s]+$",lastName);
		boolean passwordFormatChecker = Pattern.matches("^[^\\s]+$",password);
		
		if (birthdayFormatChecker & EmailFormatChecker & phoneFormatChecker& nameFormatChecker& passwordFormatChecker) {
		Client client = new Client(firstName,  lastName,  birthDate,  email,  phoneNumber, password);
		ResponseObject result = logisticsCompany.CreateNewClient(client);
		
		if (result.getErrorCode() == 90) {
			view.setVisible(false);
			view.showError(result.getErrorMessage());

			DisplayClientsController displayClientsController = new DisplayClientsController(this.logisticsCompany, this.sessionModel);

			DisplayClientsView invView = new DisplayClientsView(displayClientsController);
			displayClientsController.setView(invView);
			displayClientsController.display();
			
		} else {
			view.showError(result.getErrorMessage());
		}
		
		} else if(!birthdayFormatChecker) {
			view.showError("Incorrect birth date format! It has to follow the following format DD/MM/YYYY");
		
		} else if(!EmailFormatChecker) {
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
	public void setView(CompanyPersonalInfoView view) {
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




