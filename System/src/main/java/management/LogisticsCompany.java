package management;

import java.util.ArrayList;

import response.ResponseObject;

public class LogisticsCompany {
	
	
	//This is to insure the company follows the singleton design pattern
	private static LogisticsCompany instance;
	//Company's attributes
	private String name;
	private int count ;

	private ClientDatabase clientDatabase;
	private ContainerDatabase containerDatabase;
	private JourneyDatabase journeyDatabase;
	
	//Constructor that pulls the latest changes from the json files.
	public LogisticsCompany() {
		
		clientDatabase = new ClientDatabase();
		containerDatabase = new ContainerDatabase();
		journeyDatabase = new JourneyDatabase();
		
		try {
			clientDatabase.pull();
			containerDatabase.pull();
			journeyDatabase.pull();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		count = clientDatabase.getClients().size() + 1 ;
		
	}
	//This insures the uniqueness of client id
		public void countIncrement() {
			this.count++;
		}
	//Getters and setter
	public String getName() {
		return name;	
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCount() {
	return this.count;
	}
	
	
	public ClientDatabase getClientDatabase() {
		return clientDatabase;
	}
	public void setClientDatabase(ClientDatabase clientDatabase) {
		this.clientDatabase = clientDatabase;
	}
	
	public ContainerDatabase getContainerDatabase() {
		return containerDatabase;
	}
	public void setContainerDatabase(ContainerDatabase containerDatabase) {
		this.containerDatabase = containerDatabase;
	}

	public JourneyDatabase getJourneyDatabase() {
		return journeyDatabase;
	}
	public void setJourneyDatabase(JourneyDatabase journeyDatabase) {
		this.journeyDatabase = journeyDatabase;
	}
	
	
	//This method produces a fresh client Database.
	public void cleanDataBase() {
		
		try {
			clientDatabase.produce();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.count = 1;
		
	}
	//This method is used by the company to create a client and add it to their database 
	//It returns a response Object corresponding to whether the creation is legal or not and gives the appropriate error codes and response for each of the cases.
	public ResponseObject CreateNewClient(Client client) throws Exception {
		
		ResponseObject response;
		
		if (clientDatabase.legalPhoneNumberToAddClient(client) & clientDatabase.legalEmailToAddClient(client) & client.getfirstName() != null & client.getlastName() != null & client.getEmail() !=null & client.getPhoneNumber() != null) {
			//reg.clients.add(client);
			client.setId(count);
			clientDatabase.getClients().add(client);
			clientDatabase.push();
			countIncrement();
			
			response = new ResponseObject(1000, "Client Added Successfully");;

		} else if (client.getfirstName() == null){
			response = new ResponseObject(1001, "Client Not Added. Missing name.");

			
		} else if (client.getlastName() == null){
			response = new ResponseObject(1001, "Client Not Added. Missing name.");

			
		} else if (client.getEmail() == null | client.getPhoneNumber() == null){
			response = new ResponseObject(1002, "Client Not Added. Missing contact information. Please provide an email or a phone number.");

		} else if (! clientDatabase.legalPhoneNumberToAddClient(client)){
			response = new ResponseObject(1003, "Client Not Added. Phone number already used by a different client");

		} else {
			response = new ResponseObject(1003, "Client Not Added. Email already used by a different client");

		}
		
		return response;
		
	}
	
	
	//this is to insure the the company follows a singleton design pattern
	public static LogisticsCompany getInstance() {
		if (instance == null) {
			instance = new LogisticsCompany();
		}
		return instance;
	}

}
