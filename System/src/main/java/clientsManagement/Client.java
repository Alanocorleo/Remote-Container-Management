package clientsManagement;

public class Client {
	private String name;
	private int BirthDate;
	private String email;
	private int PhoneNumber;
	
	
	int id;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
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

	
	

}
