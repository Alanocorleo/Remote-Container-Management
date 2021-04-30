package controller;

import java.util.ArrayList;

import management.Container;
import management.LogisticsCompany;
import view.HistoryView;

public class HistoryController {

	
	private LogisticsCompany logisticCompany;
	//private Session sessionModel;
	private HistoryView view;
	private int containerId;
	
	
	public HistoryController(LogisticsCompany logisticCompany, Session session, int id) {
		this.logisticCompany = logisticCompany;
		//this.sessionModel = session;
		this.containerId = id;
	}
	
	public void setView(HistoryView view) {
		this.view  = view;
		//this.view.setSession(sessionModel);
	}

	public void display() {
		view.setVisible(true);
	}
	
	
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
		
	
		
		
		
	}
	
