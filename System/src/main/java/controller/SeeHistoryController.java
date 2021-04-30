package controller;

import java.util.ArrayList;

import management.Container;
import management.LogisticsCompany;
import view.AddHistoryView;
import view.ContainerErrorGUI;
import view.HistoryView;
import view.SeeHistoryView;

public class SeeHistoryController {

	
	
	private LogisticsCompany logisticCompany;
	private Session sessionModel;
	private SeeHistoryView view;
	
	
	
	public SeeHistoryController(LogisticsCompany logisticCompany, Session session) {
		this.logisticCompany = logisticCompany;
		this.sessionModel = session;
	}
	
	public void setView(SeeHistoryView view) {
		this.view  = view;
		this.view.setSession(sessionModel);
	}

	public void display() {
		view.setVisible(true);
	}
	
	
	
	public void showHistory(int id) {
		
		ArrayList<Container> containers = new ArrayList<Container>();
		containers = logisticCompany.getContainerDatabase().getContainers();
		
		
		int count = 0;
		for(int i = 0; i < containers.size(); i++) {
			
			
			if (containers.get(i).getContainerID() == id) {
				
				// pass the id to the next view / controller combo
				
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
	
	
	
	
	
	
	

}
