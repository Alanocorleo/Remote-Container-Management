package journeysManagement;

import clientsManagement.Client;

public class Finder {

	private Client client;

	public Finder(Client client) {
		this.client = client;
	}
	
	public Record getJourney(String criteria, String entry, Record record) {
		
		Record myJourneys = new Record();
		myJourneys.setRecord(record.filter(record.getRecord(), container -> container.getOwner() == this.client.getId()));
		
		if (criteria.equals("origin")) {
			myJourneys.setRecord(record.filter(record.getRecord(), container -> container.getOrigin().equals(entry)));
		} 
		
		else if (criteria.equals("destination")) {
			myJourneys.setRecord(record.filter(record.getRecord(), container -> container.getDestination().equals(entry)));
		}
		
		else if (criteria.equals("content-type")) {
			myJourneys.setRecord(record.filter(record.getRecord(), container -> container.getContentType().equals(entry)));
		}
		
		else if (criteria.equals("company")) {
			myJourneys.setRecord(record.filter(record.getRecord(), container -> container.getCompany().equals(entry)));
		}
		
		return myJourneys; 
		
	}

}
