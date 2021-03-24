package journeysManagement;

import java.security.SecureRandom;
import clientsManagement.Client;

public class Registration {
	
	private Client client;
	private Container container;
	private ResponseObject response;
	
	
	public Registration(Client client) {
		
		this.client = client;
		
	}

	public ResponseObject register(Container container) {
		
		if(container.getOrigin() == null){
			
			response = new ResponseObject(100, "No origin entered");
			
		}
		
		if(container.getDestination() == null){
			
			response = new ResponseObject(101, "No destination entered");
			
		}

		if(container.getCompany() == null){
			
			response = new ResponseObject(102, "No company entered");
			
		}
		
		if(container.getContentType() == null){
			
			response = new ResponseObject(103, "No content type entered");
			
		}
		
		if(!((container.getOrigin() == null)||(container.getDestination() == null)||(container.getCompany() == null)||(container.getContentType() == null))){
			
			this.container = container;
			
		}
		
		return response;
		
		
            
	}

	public void createJourney() {
		
		SecureRandom output = new SecureRandom();
		int cipher = output.nextInt(100000);
		String journeyID = container.getOrigin().charAt(0) + container.getDestination().charAt(0) + String.valueOf(cipher);
		container.setJourneyID(journeyID);
		       
	}

	

}
