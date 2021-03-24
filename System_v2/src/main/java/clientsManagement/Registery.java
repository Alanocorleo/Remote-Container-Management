package clientsManagement;


import java.util.ArrayList;

public class Registery {
	private int count = 1;


	
	private ArrayList <Client> registery = new ArrayList <Client>();
	
	
	public void addClient(Client c) {
		if (ClientExists(c)) {
			
			
		}else {
			c.setId(count);
			registery.add(c);
			count++;
		}
		
		
	}
	
	public boolean ClientExists(Client c) {
		
		return registery.contains(c);
		
	}

}



