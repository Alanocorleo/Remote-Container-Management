package clientsManagement;


import java.util.ArrayList;

public class Registery {
	private int count = 1;


	
	private ArrayList <Client> registery = new ArrayList <Client>();
	
	
	public void addClient(Client c) {
		if (this.checkContactInfoOfClients(c)== 0 & c.getName() != null & (c.getEmail() !=null | c.getPhoneNumber() != 0)) {
			c.setId(count);
			registery.add(c);
			count++;
			
		}else {
			
		}
		
		
	}
	
	public boolean ClientExists(Client c) {
		
		return  (checkInstancesofClientinRegistery(c) == 1);
		
	}
	
	public boolean checkID(Client c) {
		int uniqueId = c.getId(); 
		int counter = 0;
		for (Client clients : registery) {
			if (clients.getId()==uniqueId) {
				counter ++;
			}
		}
		return (counter == 1);
	}
	//name is misleading
	public int checkContactInfoOfClients(Client c) {
		int counter = 0;
		for (Client clients : registery) {
			if (clients.getPhoneNumber()== c.getPhoneNumber() | clients.getEmail() == c.getEmail()) {
				counter ++;
			}
		}
		return (counter);
	}
	public void printer() {
		for (Client clients : registery) {
			System.out.println(clients);
		}
	}
	
	public int checkInstancesofClientinRegistery(Client c) {
		int counter = 0;
		for (Client clients : registery) {
			if (clients.getPhoneNumber()== c.getPhoneNumber() & clients.getEmail() == c.getEmail() & clients.getId() == c.getId() & clients.getBirthDate() == c.getBirthDate() & clients.getName() == c.getName()  ) {
				counter ++;
			}
		}
		return (counter);
	}
	
	public boolean checkUpdatedInfo(String name, int BirthDate, String email, int PhoneNumber,String ReferencePerson , String Address) {
		for (Client clients : registery) {
			if (clients.getPhoneNumber()== PhoneNumber & clients.getEmail() == email & clients.getBirthDate() == BirthDate & clients.getName() == name & clients.getReferencePerson() == ReferencePerson & clients.getAddress() == Address) {
				return true;
			}
		}
		return (false);
		
		
	}
	
	public boolean allowedUpdate(String email, int PhoneNumber) {
		for (Client clients : registery) {
			if (clients.getPhoneNumber() == PhoneNumber ) {
				return false;
			}else if (clients.getEmail() == email){
				return false;
				
			}
			}
		return true;
		}
	
	
	public ArrayList <Client> getInfoClientbyId(int Id) {
		ArrayList <Client> listOfClientMatchingId = new ArrayList <Client>();
		for (Client clients : registery) {
			if (clients.getId() == Id) {
				listOfClientMatchingId.add(clients);
			}
		}
		return listOfClientMatchingId;
		}
	
	
	
	public ArrayList <Client> getInfoClientbyName(String name) {
		ArrayList <Client> listOfClientMatchingName = new ArrayList <Client>();
		for (Client clients : registery) {
			if (clients.getName() == name) {
				listOfClientMatchingName.add(clients);
			}
		}
		return listOfClientMatchingName;
		}
}

	
	




