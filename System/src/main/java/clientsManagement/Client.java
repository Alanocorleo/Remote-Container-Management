package clientsManagement;

import java.util.ArrayList;
import journeysManagement.Container;
import journeysManagement.ContainerDatabase;
import journeysManagement.ResponseObject;


public class Client {
	
	private String name = null;
	private int BirthDate;
	private String email = null;
	private int PhoneNumber = 0;
	private String ReferencePerson;
	private String Address;
	private int id;
	private ArrayList<Container> myContainers = new ArrayList<Container>();

	public Client(String name, int BirthDate, String email, int PhoneNumber) {		
		this.name= name;
		this.BirthDate = BirthDate;
		this.email = email;
		this.PhoneNumber = PhoneNumber;
	}
	
	public Client(String name, int BirthDate, int PhoneNumber) {		
		this.name= name;
		this.BirthDate = BirthDate;
		this.PhoneNumber = PhoneNumber;
	}
	
	public Client(String name, int BirthDate, String email) {		
		this.name= name;
		this.BirthDate = BirthDate;
		this.email = email;
	}
	
	public Client(String name, int BirthDate, String email, int PhoneNumber,String ReferencePerson , String Address) {		
		this.name= name;
		this.BirthDate = BirthDate;
		this.email = email;
		this.PhoneNumber = PhoneNumber;
		this.Address = Address;
		this.ReferencePerson = ReferencePerson;
	}
	
	public Client() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(int birthDate) {
		BirthDate = birthDate;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public int getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	
	public String getReferencePerson() {
		return ReferencePerson;
	}

	public void setReferencePerson(String referencePerson) {
		ReferencePerson = referencePerson;
	}

	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	
	public String toString() {
		return "The Id is: "+ this.id +"\n"+ "The name is: "+this.name +"\n" + "this is the address" + this.Address ; 
	}

	
	public void update(String email, int PhoneNumber,String ReferencePerson , String Address, Registery registery) {
		
		if (registery.allowedUpdate(email, PhoneNumber)) {
		this.email = email;
		this.PhoneNumber = PhoneNumber;
		this.Address = Address;
		this.ReferencePerson = ReferencePerson;
		}
		
	}
	
	public ArrayList<Container> getMyContainers() {
		return myContainers;
	}

	public void setMyContainers(ArrayList<Container> myContainers) {
		this.myContainers = myContainers;
	}

	public ResponseObject registerContainers(String location, String contentType, String company, int quantity, ContainerDatabase containers) {
		
		ResponseObject response;
		
		if (!((location == null) || (contentType == null) || (company == null) || (quantity == 0))){
			
			response = new ResponseObject(111, "Container not found");
			
			for (Container container : containers.extract(quantity, location)) {
				container.setOwner(id);
				container.setContentType(contentType);
				container.setCompany(company);
				this.myContainers.add(container);
				// Codes from 000 to 099 indicate successful operation
				response = new ResponseObject(010, "Container has been registered");
			}
			
		}
		
		else {
			
			// Codes from 100 to 999 indicate otherwise
			response = new ResponseObject(110, "Necessary parameters not entered");
			
		}
			 
		return response;
		
	}
	

}
