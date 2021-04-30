package management;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import response.ResponseObject;


public class Client {
	
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
	
	public void updateInfo(String password, ClientDatabase registery) {
		this.password = password;
		try {
			registery.push();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
	
	public void updateFriendsList(int id) {
		myFriends.add(id);
	}
	
	public ArrayList<Client> getMyFriends(ClientDatabase registery){
		ArrayList<Client> ListofFriendClients = new ArrayList<Client>();
		for (int friend : this.myFriends) {
			ListofFriendClients.add(registery.getClient(friend).get(0));
			
		}
		return ListofFriendClients;
	}

}

