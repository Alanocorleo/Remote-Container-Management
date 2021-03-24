package journeysManagement;

import clientsManagement.Client;

public class Container {
	
	private int owner;
	private String origin;
	private String destination;
	private String contentType;
	private String company;
	private String journeyID;
	
	
	public Container() {
		super();
	}
	
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	public String getJourneyID() {
		return journeyID;
	}
	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}


}
