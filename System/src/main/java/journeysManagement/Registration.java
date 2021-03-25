package journeysManagement;

import java.security.SecureRandom;
import clientsManagement.Client;

public class Registration {
	
	private Client client;
	private Container container;
	private ResponseObject response;
	
	
	public Registration(Client client) {
		
		setClient(client);
		
	}

	public ResponseObject register(Container container) {
		
		if(!((container.getOrigin() == null)||(container.getDestination() == null)||(container.getCompany() == null)||(container.getContentType() == null))){
			
			// Codes from 000 to 100 indicate successful operation
			response = new ResponseObject(010, "Container has been registered");
			this.container = container;
			
		}
		
		else {
			
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(110, "Some necessary parameters are not entered");
			
		}
		
		return response;
		  
	}

	public void createJourney() {
		
		SecureRandom output = new SecureRandom();
		int cipher = output.nextInt(100000);
		String journeyID = (String.format("%c%c", container.getOrigin().charAt(0), container.getDestination().charAt(0)) + String.valueOf(cipher));
		container.setJourneyID(journeyID);
			       
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
