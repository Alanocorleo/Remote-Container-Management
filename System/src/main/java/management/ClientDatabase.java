package management;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientDatabase extends AbstractTableModel {
	
	private static final long serialVersionUID = 1003884717778953988L;
 
	final String database = "client_database.json";
	
	@JsonProperty("clients")
	private ArrayList <Client> clients;
	
	@JsonCreator
	public ClientDatabase() {
		setClients(new ArrayList <Client>());
	}
	
	//getters and setters
	public ArrayList <Client> getClients() {
		return clients;
	}
	
	public void setClients(ArrayList <Client> clients) {
		this.clients = clients;
	}
	
	//This method is used to produce a new database it is also used to remove an existing database and create a new fresh empty database
	public void produce() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		this.clients = new ArrayList <Client>();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	//This method is used to fetch the information from the json file to the class
	public void pull() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ClientDatabase clients = new ClientDatabase();
		
		clients = mapper.readValue(new File(database), ClientDatabase.class);
		this.clients = clients.getClients();
		
	}
	//This method is used to save the changes in this class to the json file
	public void push() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		Files.write(Paths.get(database), jsonResult.getBytes());
	}
	
	
	//This method checks if registered client has the same phone number of email as the to be updated one. This is to insure that emails and phonenumbers are unique
	public boolean allowedUpdate(int id, String emailOrPhoneNumber) {
		for (Client perticularClient : clients) {
			if (perticularClient.getEmail().equals(emailOrPhoneNumber) & perticularClient.getId() != id){
				return false;
				
			}else if(perticularClient.getPhoneNumber().equals(emailOrPhoneNumber) ){
				return false;
			}
			}
		return true;
		}
	
	
	//password getter
	public String getPassword(String email) {
		for (Client perticularClient : clients) {
			
			if (perticularClient.getEmail().equals(email)) {
				return perticularClient.getPassword();
			}
		}
		return null;
	}
	
	//This method is used by the logistics company to make sure the registed clients all have a unique Email

	public boolean legalEmailToAddClient(Client c) {
		int counter = 0;
		for (Client perticularClient : clients) {
			if (perticularClient.getEmail().equals(c.getEmail())) {
				counter ++;
			}
		}
		return (counter == 0  );
	}
	//This method is used by the logistics company to make sure the registed clients all have a unique phone nunmber

	public boolean legalPhoneNumberToAddClient(Client c) {
		int counter = 0;
	
		for (Client perticularClient : clients) {
			if (perticularClient.getPhoneNumber().equals(c.getPhoneNumber())) {
				counter ++;
			}
		}
		return (counter == 0 );
	}

	//The following methods are used for the BDD tests

	public boolean ClientExists(Client c) {
		return  (checkInstancesofClientinRegistery(c) != 0);
	}
	
	public boolean checkID(Client c) {
		int uniqueId = c.getId(); 
		int counter = 0;
		for (Client Client : clients) {
			if (Client.getId()==uniqueId) {
				counter ++;
			}
		}
		return (counter == 1);
	}

	public int checkInstancesofClientinRegistery(Client c) {
		int counter = 0;
		for (Client Client : clients) {
			if (Client.getPhoneNumber()== c.getPhoneNumber() & Client.getEmail() == c.getEmail() & Client.getId() == c.getId() & Client.getBirthDate() == c.getBirthDate() & Client.getfirstName() == c.getfirstName() & Client.getlastName() == c.getlastName()  ) {
				counter ++;
			}
		}
		return (counter);
	}
	
	public boolean checkUpdatedInfo(String firstName, String lastName, String BirthDate, String email, String PhoneNumber) {
		for (Client Client : clients) {
			if (Client.getPhoneNumber()== PhoneNumber & Client.getEmail() == email & Client.getBirthDate() == BirthDate & Client.getfirstName() == firstName & Client.getlastName() == lastName ) {
				return true;
			}
		}
		return (false);
		
		
	}
	
	
	// The getClient method is overloaded so it is capable of returning a Client array list of all clients who match the searching input
	
	//For getting clients by Id the output would either be empty or contains one element due to the uniqueness of id 
	public ArrayList <Client> getClient(int Id) {
		ArrayList <Client> listOfClientMatchingId = new ArrayList <Client>();
		for (Client Client : this.getClients()) {
			if (Client.getId() == Id) {
				listOfClientMatchingId.add(Client);
			}
		}
		return listOfClientMatchingId;
	}
	
	//For getting clients by email the output would either be empty or contains one element due to the uniqueness of email

	public ArrayList <Client> getClient(String email) {
		ArrayList <Client> listOfClientMatchingName = new ArrayList <Client>();
		for (Client Client : this.getClients()) {
			if (Client.getEmail().equals(email) ) {
				listOfClientMatchingName.add(Client);
			}
		}
		return listOfClientMatchingName;
	}
	//For getting clients by first and last name the output can be an ArrayList of more than one client in case multiple clients have the same name

	public ArrayList <Client> getClient(String firstName,String lastName) {
		ArrayList <Client> listOfClientMatchingName = new ArrayList <Client>();
		for (Client Client : this.getClients()) {
			if (Client.getfirstName().equals(firstName) &  (Client.getlastName().equals(lastName))) {
				listOfClientMatchingName.add(Client);
			}
		}
		return listOfClientMatchingName;
	}
	
	
	//The following methods are used for defining the Jtable 
	@JsonIgnore
	@Override
	public int getRowCount() {
		return clients.size();
	}

	@JsonIgnore
	public int getColumnCount() {
		return 6;
	}
	@JsonIgnore
	@Override
	public TableModelListener[] getTableModelListeners() {
        return listenerList.getListeners(TableModelListener.class);
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Client perticularClient = clients.get(rowIndex);
		
		if (columnIndex == 0) {
			return perticularClient.getId();
			
		} else if (columnIndex == 1) {
			return perticularClient.getfirstName();
			
		} else if (columnIndex == 2) {
			return perticularClient.getlastName();
			
		} else if (columnIndex == 3) {
			return perticularClient.getBirthDate();
			
		} else if (columnIndex == 4) {
			return perticularClient.getEmail();
			
		} else if (columnIndex == 5) {
			return perticularClient.getPhoneNumber();
			
		} else {
			return null;
		}
		
	}
	
	public String getColumnName(int columnIndex) {
		
		if (columnIndex == 0) {
			return "Client ID";
			
		} else if (columnIndex == 1) {
			return "First name";
			
		} else if (columnIndex == 2) {
			return "Last name";
			
		} else if (columnIndex == 3) {
			return "Date of birth";
			
		} else if (columnIndex == 4) {
			return "Email";
			
		} else if (columnIndex == 5) {
			return "Phone number";
			
		} else {
			return null;
		}
		
	}
	
}


	