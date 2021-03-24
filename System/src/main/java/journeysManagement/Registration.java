package journeysManagement;

import java.security.SecureRandom;
import clientsManagement.Client;

public class Registration {
	
	private Client client;
	private Container container;
	
	public Registration(Client client) {
		this.client = client;
	}

	public void register(Container container) {
		this.container = container;
            
	}
	
	public void createJourney() {
		
		SecureRandom output = new SecureRandom();
		int cipher = output.nextInt(100000);
		String journeyID = container.getOrigin().charAt(0) + container.getDestination().charAt(0) + String.valueOf(cipher);
		container.setJourneyID(journeyID);
		       
	}
	

}
