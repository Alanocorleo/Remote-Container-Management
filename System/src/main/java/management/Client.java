package management;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import response.ResponseObject;


public class Client {
	//Clients attributes:
	private int id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private String password;
	private String phoneNumber ;

	private ArrayList<Container> myContainers = new ArrayList<Container>();
	private JourneyDatabase myJourneys = new JourneyDatabase();
	
	@JsonProperty("friends")
	private ArrayList<Integer> myFriends = new ArrayList<Integer>();	
	
	
	//Constructors
	public Client() {
		super();
	}
	
	public Client(String firstName, String lastName, String BirthDate, String email, String PhoneNumber) {		
		this.firstName= firstName;
		this.lastName= lastName;
		this.birthDate = BirthDate;
		this.email = email;
		this.phoneNumber = PhoneNumber;
		this.password = BirthDate;
		
	}
	public Client(String firstName, String lastName, String BirthDate, String email, String PhoneNumber, String password) {		
		this.firstName= firstName;
		this.lastName= lastName;
		this.birthDate = BirthDate;
		this.email = email;
		this.phoneNumber = PhoneNumber;
		this.password = password;
		
	}
	//getters and setters
	public int getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}
	
	public String getfirstName() {
		return this.firstName;
	}
	@JsonProperty("fistName")
	public void setfirstName(String name) {
		this.firstName=name;
	}
	
	public String getlastName() {
		return this.lastName;
	}
	@JsonProperty("lastName")
	public void setlastName(String name) {
		this.lastName=name;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	@JsonProperty("birthDate")
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getPassword() {
		return password;
	}
	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Container> getMyContainers() {
		return myContainers;
	}
	public void setMyContainers(ArrayList<Container> myContainers) {
		this.myContainers = myContainers;
	}
	
	public JourneyDatabase getMyJourneys() {
		return myJourneys;
	}
	public void setMyJourneys(JourneyDatabase myJourneys) {
		this.myJourneys = myJourneys;
	}
		
	//The following method returns a response object corresponding to whether the personal information of the client has been updated or not. 
	//In case we fail to update the information, we return a corresponding error code and message
	public ResponseObject updateInfo(String firstName,String lastName, String birthDate, String email, String PhoneNumber, ClientDatabase registery) throws Exception {
		ResponseObject response;
		
		if (registery.allowedUpdate(this.id,email) & registery.allowedUpdate(this.id,PhoneNumber)) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = PhoneNumber;
		
		response = new ResponseObject(1004, "Information Updated successfully");
		registery.push();
		
		} else if(!registery.allowedUpdate(this.id, email) ) {
			response = new ResponseObject(1005, "Information not updated. Email already used by an existing client");

		} else {
			response = new ResponseObject(1006, "Information not updated. Phone number already used by an existing client");
		}
		
		return response;
		
	}
	
	//This method is used to change the password
	//It could have been just merged wit the method above but the password attribute was not created yet.
	public void updateInfo(String password, ClientDatabase registery) {
		this.password = password;
		try {
			registery.push();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//This method is used to find an existing client in database by an email
	//It is used in conjunction with the updateFriendList method to implement the information sharing functionality
	//this method is used by the client who want to share their information they do not necessarily have access to the information of who ever they send their info to
	public ResponseObject addFriend(String email, ClientDatabase registery) {
		
		ResponseObject response;
		ArrayList<Client> theToBeAddedFriend = registery.getClient(email);
		if (theToBeAddedFriend.isEmpty()){
			response = new ResponseObject(11021, "Friend not found!!!");
			
			}
		
		
		else {
			// Codes from 100 to 999 indicate otherwise
			theToBeAddedFriend.get(0).updateFriendsList(this.id);
			try {
				registery.push();
			} catch (Exception e) {
				e.printStackTrace();
			}
			response = new ResponseObject(11560, "Now you friend can see your information");
		}
			 
		return response;
		
	}
	
	//This method is used to update the list of client who this client has access to their information
	public void updateFriendsList(int id) {
		if (myFriends.contains(id)) {}
		else{
		myFriends.add(id);
		}
	}
	
	
	//This method converts the array list of clients ids into an array list of Clients
	public ArrayList<Client> getMyFriends(ClientDatabase registery){
		ArrayList<Client> ListofFriendClients = new ArrayList<Client>();
		for (int friend : this.myFriends) {
			ListofFriendClients.add(registery.getClient(friend).get(0));
			
		}
		return ListofFriendClients;
	}

}

