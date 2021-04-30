package controller;

import management.LogisticsCompany;
import view.MainMenuCompanyView;
import view.SeeHistoryView;
import view.AddHistoryView;
import view.DisplayClientsView;
import view.DisplayContainersView;
import view.DisplayJourneysCompanyView;

public class MainMenuCompanyController {
		
	    private LogisticsCompany logisticsCompany;
		private Session sessionModel;
		private MainMenuCompanyView MainMenuview;
		private DisplayClientsController displayClientsController;
		private DisplayContainersController displayContainersController;
		private DisplayJourneysCompanyController displayJourneysController;
		private AddHistorycontroller addHistorycontroller;
		private SeeHistoryController seeHistoryController;

		public MainMenuCompanyController(LogisticsCompany logisticCompany, Session session) {
			this.logisticsCompany = logisticCompany;
			this.sessionModel = session;
		}

		public void setView(MainMenuCompanyView MainMenuview) {
			this.MainMenuview  = MainMenuview;
			this.MainMenuview.setSession(sessionModel);
		}

		public void display() {
			MainMenuview.setVisible(true);
		}
		
		public void displayClients() {
			displayClientsController = new DisplayClientsController(this.logisticsCompany, this.sessionModel);

			DisplayClientsView invView = new DisplayClientsView(displayClientsController);
			displayClientsController.setView(invView);
			displayClientsController.display();
		}
		
		public void displayContainers() {
			displayContainersController = new DisplayContainersController(this.logisticsCompany, this.sessionModel);

			DisplayContainersView invView = new DisplayContainersView(displayContainersController);
			displayContainersController.setView(invView);
			displayContainersController.display();
		}
		
		public void displayJourneys() {
			displayJourneysController = new DisplayJourneysCompanyController(this.logisticsCompany, this.sessionModel);

			DisplayJourneysCompanyView invView = new DisplayJourneysCompanyView(displayJourneysController);
			displayJourneysController.setView(invView);
			displayJourneysController.display();
		}
		
		public void displayAddHistory() {
			addHistorycontroller = new AddHistorycontroller(this.logisticsCompany, this.sessionModel);

			AddHistoryView invView = new AddHistoryView(addHistorycontroller);
			addHistorycontroller.setView(invView);
			addHistorycontroller.display();
			
		
	}

		public void displaySeeHistory() {
			seeHistoryController = new SeeHistoryController(this.logisticsCompany, this.sessionModel);

			SeeHistoryView invView = new SeeHistoryView(seeHistoryController);
			seeHistoryController.setView(invView);
			seeHistoryController.display();
		}
		
	}



