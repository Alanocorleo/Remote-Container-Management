package controller;

import java.util.ArrayList;

import management.Container;
import management.LogisticsCompany;
import view.AddHistoryView;
import view.ContainerErrorGUI;

/**
 * This class is responsible for adding history to a container
 * by the logistics company.
 */

public class AddHistorycontroller {
	
	private LogisticsCompany logisticsCompany;
	private Session sessionModel;
	private AddHistoryView view;

	/**
	 * This constructor assigns responsibility to the logistics company, and sets
	 * the current session.
	 * @param logisticsCompany
	 * @param session
	 */
	public AddHistorycontroller(LogisticsCompany logisticsCompany, Session session) {
		this.logisticsCompany = logisticsCompany;
		this.sessionModel = session;
	}

	/**
	 * This method finds the specified container, and appends the new data to its 
	 * history. In case of not finding the container, it returns an error pop up.
	 * @param date
	 * @param id
	 * @param temp
	 * @param humid
	 * @param press
	 */
	public void submitChanges(String date, int id, int temp, int humid, int press) {
		ArrayList<Container> containers = new ArrayList<Container>();
		containers = logisticsCompany.getContainerDatabase().getContainers();

		int count = 0;
		for(int i = 0; i < containers.size(); i++) {

			if (containers.get(i).getContainerID() == id) {
				containers.get(i).setDate(date);
				containers.get(i).setTemperature(temp);
				containers.get(i).setHumidity(humid);
				containers.get(i).setPressure(press);
				containers.get(i).appendHistory();
				containers.get(i).setHumidity(humid);
				count++;
				view.setVisible(false);
				
				try {
					logisticsCompany.getContainerDatabase().push();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (count == 0) {
			new ContainerErrorGUI();
		}
	}

	/**
	 * This method sets the corresponding view and view's session.
	 * @param view
	 */
	public void setView(AddHistoryView view) {
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