package controller;

import java.util.regex.*;  
import management.Client;

import management.LogisticsCompany;
import response.ResponseObject;
import view.CompanyPersonalInfoView;
import view.DisplayClientsView;




	public class CompanyPersonalInfoController {
		//Attributes
		private Session sessionModel;
		private CompanyPersonalInfoView view;
		
		private LogisticsCompany company;
		//Constructor
		public CompanyPersonalInfoController(  Session session,  LogisticsCompany company) {
		
			this.company =company;
			this.sessionModel = session;
		}
		//setter
		public void setView(CompanyPersonalInfoView view) {
			this.view = view;
			this.view.setSession(sessionModel);
		}
		//Method used to check for right fomat and gets access to the model (LogisticsCompany class) in order to register a client
		public void createInfo(String FirstName, String LastName, String BirthDate, String Email, String PhoneNumber, String password ) throws Exception {
//			boolean birthdayFormatChecker = Pattern.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})", BirthDate);
			boolean birthdayFormatChecker = Pattern.matches("^((3[0-1]|2[0-9]|1[0-9]|[1-9])/(1[0-2]|[1-9])/([0-9]{4}))$", BirthDate);
			boolean EmailFormatChecker = Pattern.matches("^(.+)@(.+)$", Email);
			boolean phoneFormatChecker = Pattern.matches("([0-9]{8,}+)", PhoneNumber);
			boolean nameFormatChecker = Pattern.matches("^[^\\d\\s]+$",FirstName) & Pattern.matches("^[^\\d\\s]+$",LastName);
			boolean passwordFormatChecker = Pattern.matches("^[^\\s]+$",password);
			
			if (birthdayFormatChecker & EmailFormatChecker & phoneFormatChecker& nameFormatChecker& passwordFormatChecker) {
			Client client = new Client(FirstName,  LastName,  BirthDate,  Email,  PhoneNumber, password);
			ResponseObject result = company.CreateNewClient(client);
			if (result.getErrorCode() == 1000) {
				view.setVisible(false);
				view.showError(result.getErrorMessage());

				DisplayClientsController displayClientsController = new DisplayClientsController(this.company, this.sessionModel);

				DisplayClientsView invView = new DisplayClientsView(displayClientsController);
				displayClientsController.setView(invView);
				displayClientsController.display();
			} else {
				view.showError(result.getErrorMessage());
			}
			}else if(!birthdayFormatChecker) {
				view.showError("Incorrect birth date format! It has to follow the following format DD/MM/YYYY");
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
		
		
		//Display the company personal information page 

		public void display() {
			view.setVisible(true);
		}
	

	}




