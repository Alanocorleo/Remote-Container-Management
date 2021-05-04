package management;

import response.ResponseObject;

/**
 * LogisticsCompany class represent the logistics company who will control the program.
 */

public class LogisticsCompany {
	
	// This is to insure the company follows the singleton design pattern.
	private static LogisticsCompany instance;
	
	// Company's attributes
	private String name;
	private int count ;

	private ClientDatabase clientDatabase;
	private ContainerDatabase containerDatabase;
	private JourneyDatabase journeyDatabase;
	
	// Constructor that pulls the latest changes from the JSON files
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
	
	// This insures the uniqueness of client-ID.
	public void countIncrement() {
		this.count++;
	}
	
	// Getters and setter
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
	
	/**
	 * This method produces a fresh client Database.
	 */
	public void cleanDataBase() {
		try {
			clientDatabase.produce();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.count = 1;
	}
	
	/**
	 * This method is used by the company to create a client and add it to their 
	 * database. It returns a response Object corresponding to whether the creation
	 * is legal or not and gives the appropriate error codes and response for each 
	 * of the cases.
	 * @param client
	 * @return execution response
	 * @throws Exception
	 */
	public ResponseObject CreateNewClient(Client client) throws Exception {
		
		ResponseObject response;
		
		if (clientDatabase.legalPhoneNumberToAddClient(client) & clientDatabase.legalEmailToAddClient(client) & client.getfirstName() != null & client.getlastName() != null & client.getEmail() !=null & client.getPhoneNumber() != null) {
			client.setId(count);
			clientDatabase.getClients().add(client);
			clientDatabase.push();
			countIncrement();
			
			response = new ResponseObject(90, "Client has been added successfully");;

		} else if (client.getfirstName() == null){
			response = new ResponseObject(900, "Client has not been added. Missing first name");

			
		} else if (client.getlastName() == null){
			response = new ResponseObject(901, "Client has not been added. Missing last name");

			
		} else if (client.getEmail() == null | client.getPhoneNumber() == null){
			response = new ResponseObject(902, "Client has not been added. Missing contact information. Please provide an email or a phone number");

		} else if (! clientDatabase.legalPhoneNumberToAddClient(client)){
			response = new ResponseObject(903, "Client has not been added. Phone number already used by a different client");

		} else {
			response = new ResponseObject(904, "Client has not been added. Email already used by a different client");

		}
		
		return response;
		
	}
	
	/**
	 * This is to insure the the company follows a singleton design pattern.
	 * @return instance
	 */
	public static LogisticsCompany getInstance() {
		if (instance == null) {
			instance = new LogisticsCompany();
		}
		return instance;
	}
	
}
