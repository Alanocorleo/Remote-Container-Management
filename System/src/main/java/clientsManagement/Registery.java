package clientsManagement;


import java.util.ArrayList;

public class Registery {


	
	private ArrayList <Client> registery = new ArrayList <Client>();
	
	public void addClient(Client c) {
		
		registery.add(c);
	}
	
	public boolean ClientExists(Client c) {
		
		return registery.contains(c);
		
	}
	

}



