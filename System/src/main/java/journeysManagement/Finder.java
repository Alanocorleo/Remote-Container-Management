package journeysManagement;

import clientsManagement.Client;

public class Finder {

	private Client client;
	private Record record;

	public Finder(Client client, Record record) {
		this.client = client;
		this.record = record;
	}
	
	public Record getJourneys(String criteria, String entry) {
		
		Record myJourneys = new Record();
		myJourneys.setRecord(this.record.filter(this.record.getRecord(), container -> container.getOwner() == this.client.getId()));
		
		if (criteria.equals("origin")) {
			myJourneys.setRecord(myJourneys.filter(myJourneys.getRecord(), container -> container.getOrigin().equals(entry)));
		} 
		
		else if (criteria.equals("destination")) {
			myJourneys.setRecord(myJourneys.filter(myJourneys.getRecord(), container -> container.getDestination().equals(entry)));
		}
		
		else if (criteria.equals("content-type")) {
			myJourneys.setRecord(myJourneys.filter(myJourneys.getRecord(), container -> container.getContentType().equals(entry)));
		}
		
		else if (criteria.equals("company")) {
			myJourneys.setRecord(myJourneys.filter(myJourneys.getRecord(), container -> container.getCompany().equals(entry)));
		}
		
		return myJourneys; 
		
	}

}
