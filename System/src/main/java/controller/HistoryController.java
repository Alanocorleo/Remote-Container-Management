package controller;

import java.util.ArrayList;

import management.Container;
import management.LogisticsCompany;
import view.HistoryView;

/**
 * This class is responsible for showing the history book of a selected container.
 */

public class HistoryController {

	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private HistoryView view;
	private int containerId;
	
	/**
	 * This constructor calls the logistics company, and sets the current session.
	 * @param logisticsCompany
	 * @param session
	 */
	public HistoryController(LogisticsCompany logisticCompany, Session session, int id) {
		this.logisticCompany = logisticCompany;
		this.sessionModel = session;
		this.containerId = id;
	}
	
	/**
	 * This method returns a matrix of data history. Each column refers to a 
	 * measurement given as date, temperature, humidity, pressure, and position.
	 * @return matrix of container data history
	 */
	public Object[][] history() {
		
		ArrayList<Container> containers = new ArrayList<Container>();
		containers = logisticCompany.getContainerDatabase().getContainers();
		
		int count = 0;
		for(int i = 0; i < containers.size(); i++) {
			
			if (containers.get(i).getContainerID() == this.containerId) {
				return containers.get(i).getHistory().showTable();
			}
		} 
			
		return null;
		
	}
	
	/**
	 * This method sets the view.
	 * @param view
	 */
	public void setView(HistoryView view) {
		this.view  = view;
	}

	/**
	 * This method displays the window.
	 */
	public void display() {
		view.setVisible(true);
	}
			
}
	
