
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import io.cucumber.java.Before;
import management.Client;
import management.ClientDatabase;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.JourneyDatabase;
import management.LogisticsCompany;
import response.ResponseObject;


public class ClientTest {
	private Client client;
	
	private LogisticsCompany company;
	private Client myContainers;
	private ContainerDatabase containers;
	private ArrayList<Container> container;
	
	@Before
	public void createClient() {
		client = new Client();
		company = LogisticsCompany.getInstance();
		myContainers = new Client();
		containers = new ContainerDatabase();
	}
	

	@Test
	public void testGetIdandSetId() {
		client = new Client();
		client.setId(100000);
		assertTrue("Id is not 100000", client.getId() == 100000);

		
	}


	@Test
	public void testGetfirstNameandSetfirstName() {
		client = new Client();
		client.setfirstName("Alice");
		assertTrue("First name is not Alice", "Alice".equals(client.getfirstName()));
	}

	

	@Test
	public void testGetlastNameandSetlastName() {
		client = new Client();
		client.setlastName("Allinson");
		assertTrue("First name is not Allison", "Allinson".equals(client.getlastName()));
	}



	@Test
	public void testGetBirthDateandSetBirthDate() {
		client = new Client();
		client.setBirthDate("21\04\2021");
		assertTrue("Birthdate is not correct", "21\04\2021".equals(client.getBirthDate()));
	}

	@Test
	public void testGetEmailandSetEmail() {
		client = new Client();
		client.setEmail("Alice.Allinson@mail.com");
		assertTrue("Email is not correct", "Alice.Allinson@mail.com".equals(client.getEmail()));
		
	}


	@Test
	public void testGetPhoneNumberandSetPhoneNumber() {
		client = new Client();
		client.setPhoneNumber("12345678");
		assertTrue("Phone number is not correct", "12345678".equals(client.getPhoneNumber()));
	}

	

	@Test
	public void testGetPasswordandSetPassword() {
		client = new Client();
		client.setPassword("12345678");
		assertTrue("Password is not correct", "12345678".equals(client.getPassword()));
	}

	

	@Test (expected = NullPointerException.class)
	public void testGetMyContainersandSetMyContainers() {
		

		ContainerDatabase containers = new ContainerDatabase();
		ArrayList<Container> myContainers = new ArrayList<Container>();
		
		company = LogisticsCompany.getInstance();
		Client client1 = new Client();
		int counter = 1;
		for (int i = 1; i <= 10; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(counter - 1).setContainerID(counter);
			containers.getContainers().get(counter - 1).setPosition("Copenhagen");
			containers.getContainers().get(counter - 1).setAvailability(true);
			myContainers.add(container.get(i));
			counter += 1;
		}
		client1.setMyContainers(myContainers);
		
		assertTrue(myContainers.size() == client.getMyContainers().size());
			
			
	
	}


	@Test (expected = NullPointerException.class)
	public void testGetMyJourneysandSetMyJourneys() {
		JourneyDatabase journeys = new JourneyDatabase();
		company = LogisticsCompany.getInstance();
		Client client1 = new Client();
		
		Journey journey1 = new Journey();
		String journeyID1 = "CO23143";
		String origin1 = "Copenhagen";
		String destination1 = "Oslo";
		journey1.setJourneyID(journeyID1);
		journey1.setOrigin(origin1);
		journey1.setDestination(destination1);
		journey1.setDepartureDate("21/04/2021");
		journey1.setArrivalDate("22/04/2021");
		journeys.create(journey1);
		
		Journey journey2 = new Journey();
		String journeyID2 = "AC21003";
		String origin2 = "Amsterdam";
		String destination2 = "Copenhagen";
		journey2.setJourneyID(journeyID2);
		journey2.setOrigin(origin2);
		journey2.setDestination(destination2);	
		journeys.create(journey2);
		
		client1.setMyJourneys(journeys);
		assertTrue(journeys.equals(client.getMyJourneys()));
	}



	@Test
	public void testUpdateInfoStringStringStringStringStringClientDatabase() throws Exception {
		ClientDatabase clients = new ClientDatabase();
		Client client1 = new Client("Alan", "Mansour", "21/04/2021", "Alan.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.getClients().add(client1);
		Client client2 = new Client("Javier" ,"Almendra", "21/04/2021", "Javier.Almendra@mail.com", "+999-999-99-999");
		client2.setId(100001);
		clients.getClients().add(client2);
		Client client3 = new Client("Kristyn" ,"Korboe", "21/04/2021", "Kristyn.Korboe@mail.com", "+999-999-99-999");
		client3.setId(100002);
		clients.getClients().add(client3);
		clients.push();
		
		
		company = LogisticsCompany.getInstance();

		
		ResponseObject response = client1.updateInfo("Alan", "Mansour", "21/04/2021", "Alice@mail.com", "0383747858", clients);
		ResponseObject response1 = client2.updateInfo("Javier" ,"Almendra", "21/04/2021", "Alice@mail.com", "+999-999-99-999", clients);
		ResponseObject response2 = client3.updateInfo("Kristyn" ,"Korboe", "21/04/2021", "Kristyn.Korboe@mail.com", "0383747858", clients);
		assertTrue("info not updated", response.getErrorCode() == 1004);
		assertTrue("info updated", response1.getErrorCode() == 1005);
		assertTrue("info updated", response2.getErrorCode() == 1006);
	
		
	}

	@Test
	public void testUpdateInfoStringClientDatabase() {
		client = new Client();
		company = LogisticsCompany.getInstance();
		ClientDatabase registery = company.getClientDatabase();
		client.setPassword("123456");
		client.updateInfo("12345678", registery);
		assertTrue("password did not update", "12345678".equals(client.getPassword()));
	}

	@Test
	public void testAddFriend() {
		
		company = LogisticsCompany.getInstance();
		ClientDatabase registery = new ClientDatabase();
		
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client1 = new Client("Alan", "Mansour", "21/04/2021", "Alan.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		Client client2 = new Client("Javier" ,"Almendra", "21/04/2021", "Javier.Almendra@mail.com", "+999-999-99-999");
		client2.setId(100001);
		clients.add(client2);
		Client client3 = new Client("Kristyn" ,"Korboe", "21/04/2021", "Kristyn.Korboe@mail.com", "+999-999-99-999");
		client3.setId(100002);
		clients.add(client3);
		
		registery.setClients(clients);
		client = new Client();
		company = LogisticsCompany.getInstance();
		
	
		ResponseObject response = client.addFriend("Alan.Mansour@mail.com", registery);
		ResponseObject response1 = client.addFriend("AliceGOOO@mail.com", registery);
		assertTrue("Friend not Added", response.getErrorCode() == 11560);
		assertTrue("Friend Added", response1.getErrorCode() == 11021);
		
		
	}
	
	@Test
	public void testGetMyFriends() {
		
		company = LogisticsCompany.getInstance();
		ClientDatabase registery = new ClientDatabase();
		
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client1 = new Client("Dodo", "Dodo", "21/04/2021", "Dodo", "+999-999-99-990");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		Client client2 = new Client("Fodo" ,"Fodo", "21/04/2021", "Fodo.Fodo@mail.com", "+999-999-99-991");
		client2.setId(100001);
		clients.add(client2);
		Client client3 = new Client("Godo" ,"Godo", "21/04/2021", "Godo.Godo@mail.com", "+999-999-99-992");
		client3.setId(100002);
		clients.add(client3);
		
		registery.setClients(clients);
		
		try {
			registery.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		client1.addFriend("Fodo.Fodo@mail.com", registery);
		assertTrue(client2.getMyFriends(registery).size() == 1);
		assertFalse(client3.getMyFriends(registery).size() == 1);
		
		
		
	
	}

}
