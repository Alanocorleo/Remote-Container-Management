
package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import management.Container;
import management.LogisticsCompany;
import view.MainMenuCompanyView;
import view.AddHistoryView;
import view.ContainerErrorGUI;
import view.DisplayClientsView;
import view.DisplayContainersView;
//import view.DisplayJourneysView;
public class AddHistorycontroller {
	
	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private AddHistoryView view;


	public AddHistorycontroller(LogisticsCompany logisticCompany, Session session) {
		this.logisticCompany = logisticCompany;
		this.sessionModel = session;
	}

	public void submitChanges(String date, int id, int temp, int humid, int press, String pos) {



		ArrayList<Container> containers = new ArrayList<Container>();
		containers = logisticCompany.getContainerDatabase().getContainers();

		int count = 0;
		for(int i = 0; i < containers.size(); i++) {


			if (containers.get(i).getContainerID() == id) {
				
				containers.get(i).setDate(date);
				
				containers.get(i).setTemperature(temp);

				
				containers.get(i).setHumidity(humid);
				
				containers.get(i).setPressure(press);

				
				containers.get(i).setPosition(pos);


				containers.get(i).appendHistory();

				containers.get(i).setHumidity(humid);
				count++;
				view.setVisible(false);
				try {
					System.out.println("Pushing");
					logisticCompany.getContainerDatabase().push();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			
			}
		}
		if (count == 0) {
			
			new ContainerErrorGUI();
			
		}

	}

	public void setView(AddHistoryView view) {
		this.view  = view;
		this.view.setSession(sessionModel);
	}

	public void display() {
		view.setVisible(true);
	}

}