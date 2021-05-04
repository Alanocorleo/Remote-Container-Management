package controller;

import management.LogisticsCompany;
import view.MainMenuCompanyView;
import view.SeeHistoryView;
import view.AddHistoryView;
import view.DisplayClientsView;
import view.DisplayContainersView;
import view.DisplayJourneysCompanyView;

/**
 * This class is responsible for showing logistics company's main menu interface 
 * window once the logistics company is logged in.
 */

public class MainMenuCompanyController {
	
	//Attributes
    private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private MainMenuCompanyView MainMenuview;
	private DisplayClientsController displayClientsController;
	private DisplayContainersController displayContainersController;
	private DisplayJourneysCompanyController displayJourneysController;
	private AddHistorycontroller addHistorycontroller;
	private SeeHistoryController seeHistoryController;
	
	/**
	 * This constructor calls the logistics company, and sets the current session.
	 * @param logisticsCompany
	 * @param session
	 */
	//Constructor
	public MainMenuCompanyController(LogisticsCompany logisticCompany, Session session) {
		this.logisticsCompany = logisticCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method sends the user to the clients window to see all clients available
	 * in the database and managing them. 
	 */
	public void displayClients() {
		displayClientsController = new DisplayClientsController(this.logisticsCompany, this.sessionModel);

		DisplayClientsView invView = new DisplayClientsView(displayClientsController);
		displayClientsController.setView(invView);
		displayClientsController.display();
	}
	
	/**
	 * This method sends the user to the containers window to see all containers 
	 * available in the database and managing them.
	 */
	public void displayContainers() {
		displayContainersController = new DisplayContainersController(this.logisticsCompany, this.sessionModel);

		DisplayContainersView invView = new DisplayContainersView(displayContainersController);
		displayContainersController.setView(invView);
		displayContainersController.display();
	}
	
	/**
	 * This method sends the user to the journeys window to see all initiated journeys
	 * in the database and managing them.
	 */
	public void displayJourneys() {
		displayJourneysController = new DisplayJourneysCompanyController(this.logisticsCompany, this.sessionModel);

		DisplayJourneysCompanyView invView = new DisplayJourneysCompanyView(displayJourneysController);
		displayJourneysController.setView(invView);
		displayJourneysController.display();
	}
	
	/**
	 * This method sends the user to a window to add history to a container.
	 */
	public void displayAddHistory() {
		addHistorycontroller = new AddHistorycontroller(this.logisticsCompany, this.sessionModel);

		AddHistoryView invView = new AddHistoryView(addHistorycontroller);
		addHistorycontroller.setView(invView);
		addHistorycontroller.display();
	}
	
	/**
	 * This method sends the user to the history book window to see history of a container.
	 */
	public void displaySeeHistory() {
		seeHistoryController = new SeeHistoryController(this.logisticsCompany, this.sessionModel);

		SeeHistoryView invView = new SeeHistoryView(seeHistoryController);
		seeHistoryController.setView(invView);
		seeHistoryController.display();
	}
	
	/**
	 * This method sets the view and view's session.
	 * @param MainMenuview
	 */
	public void setView(MainMenuCompanyView MainMenuview) {
		this.MainMenuview  = MainMenuview;
		this.MainMenuview.setSession(sessionModel);
	}
	
	/**
	 * This method displays the window.
	 */
	public void display() {
		MainMenuview.setVisible(true);
	}
	
}



