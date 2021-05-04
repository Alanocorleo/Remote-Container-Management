package controller;

import java.util.ArrayList;

import management.Container;
import management.LogisticsCompany;
import view.ContainerErrorGUI;
import view.HistoryView;
import view.SeeHistoryView;

/**
 * This class is responsible for seeing history of a container by the logistics 
 * company.
 */

public class SeeHistoryController {
	
	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private SeeHistoryView view;
	
	/**
	 * This constructor calls the logistics company, and sets the current session.
	 * @param logisticsCompany
	 * @param session
	 */
	public SeeHistoryController(LogisticsCompany logisticCompany, Session session) {
		this.logisticCompany = logisticCompany;
		this.sessionModel = session;
	}
	
	/**
	 * This method finds the specified container, and opens a new window containing 
	 * data history. If the container is not found, it opens an error pop up.
	 * @param id
	 */
	public void showHistory(int id) {
		ArrayList<Container> containers = new ArrayList<Container>();
		containers = logisticCompany.getContainerDatabase().getContainers();
		
		int count = 0;
		for(int i = 0; i < containers.size(); i++) {
			if (containers.get(i).getContainerID() == id) {

				HistoryController historyController;
				historyController = new HistoryController(this.logisticCompany, this.sessionModel, id);

				HistoryView invView = new HistoryView(historyController);
				historyController.setView(invView);
				historyController.display();
				
				count++;
				view.setVisible(false);
			}	
		}
		
		if (count == 0) {
			new ContainerErrorGUI();
		}	
	}
	
	/**
	 * This method sets the view and view's session.
	 * @param view
	 */
	public void setView(SeeHistoryView view) {
		this.view  = view;
		this.view.setSession(sessionModel);
	}

	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}

}
