package journeysManagement;

import java.security.SecureRandom;

import clientsManagement.Client;

public class Registration {
	
	private Container container;
	private String journeyID;

	public Registration(Client client) {
	
	}

	public ResponseObject register(Container container) {
		
		ResponseObject response;
		
		if(!((container.getOrigin() == null)||(container.getDestination() == null)||(container.getCompany() == null)||(container.getContentType() == null))){
			
			// Codes from 000 to 100 indicate successful operation
			this.container = container;
			response = new ResponseObject(010, "Container has been registered");
			
		}
		
		else {
			
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(110, "Some necessary parameters are not entered");
			
		}
		
		return response;
		  
	}

	public String getJourneyID() {
		return journeyID;
	}

	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}
	
	public String createJourneyID(Record record) {
		
		SecureRandom output = new SecureRandom();
		int cipher = output.nextInt(100000);
		String journeyID = (String.format("%c%c", this.container.getOrigin().charAt(0), this.container.getDestination().charAt(0)) + String.valueOf(cipher));
		if (record.getRecord().containsKey(journeyID)) {
			return createJourneyID(record);
		}
		else 
			return this.journeyID = journeyID;
			       
	}
	
	public void upload(Record record) {
		record.getRecord().put(this.journeyID, this.container);
		}
	
}
