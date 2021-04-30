package controller;

import java.util.regex.*;  




	import javax.swing.JOptionPane;

	import management.Client;

	import management.LogisticsCompany;
import response.ResponseObject;
import view.CompanyPersonalInfoView;
import view.DisplayClientsView;




	public class CompanyPersonalInfoController {

		private Session sessionModel;
		private CompanyPersonalInfoView view;
		
		private LogisticsCompany company;
		
		public CompanyPersonalInfoController(  Session session,  LogisticsCompany company) {
		
			this.company =company;
			this.sessionModel = session;
		}


		public void setView(CompanyPersonalInfoView view) {
			this.view = view;
			//this.view.setTableModel(inventoryModel);
			this.view.setSession(sessionModel);
		}

		public void createInfo(String FirstName, String LastName, String BirthDate, String Email, String PhoneNumber, String password ) throws Exception {
			boolean birthdayFormatChecker = Pattern.matches("([0-9]{2})\\\\([0-9]{2})\\\\([0-9]{4})", BirthDate);
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
	

	}




