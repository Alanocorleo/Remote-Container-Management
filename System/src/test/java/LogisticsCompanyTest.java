
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

public class LogisticsCompanyTest {
	
	private Client client;
	private LogisticsCompany company;
	
	
	@Before
	public void createCompany() {
		client = new Client("Alice", "Allinson", "21-04-2021", "Alice@mail.com", "0383747858");
		company = LogisticsCompany.getInstance();
		company.cleanDataBase();
	
	
	}
	
	@Test
	public void getClientDatabaseandSetClientDatabase() {
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
		company.setClientDatabase(registery);
		
		assertTrue(registery.equals(company.getClientDatabase()));
		
	}
	
	@Test
	public void getContainerDatabaseandSetContainerDatabase() {
		ContainerDatabase containers = new ContainerDatabase();
		company = LogisticsCompany.getInstance();
		int counter = 1;
		for (int i = 1; i <= 10; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(counter - 1).setContainerID(counter);
			containers.getContainers().get(counter - 1).setPosition("Copenhagen");
			containers.getContainers().get(counter - 1).setAvailability(true);
			counter += 1;
		}
		company.setContainerDatabase(containers);
		
		assertNotNull(containers.equals(company.getContainerDatabase()));
	}

	@Test
	public void getJourneyDatabaseandSetJourneyDatabase() {
		JourneyDatabase journeys = new JourneyDatabase();
		company = LogisticsCompany.getInstance();
		
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
		
		company.setJourneyDatabase(journeys);
		
		assertTrue(journeys.equals(company.getJourneyDatabase()));
		
		
		
	}

	
	@Test
	public void testGetNameandSetName() {
		company = LogisticsCompany.getInstance();
		company.setName("Maersk");
		assertTrue("Company name is not Maersk", "Maersk".equals(company.getName()));
	}

	@Test
	public void testGetClientDatabaseandSetClientDatabase() {
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
		
		assertNotNull(registery.getClients().size());
		
	}

	
	@Test
	public void testCreateNewClient() throws Exception {
		company = LogisticsCompany.getInstance();
		Client client1 = new Client();
		client1.setfirstName("Sarthak");
    	client1.setlastName("Trehan");
    	client1.setBirthDate("24-02-2021");
    	client1.setEmail("alice@mail.com");
    	client1.setId(1);
    	client1.setPhoneNumber("1234567");
    	client1.setPassword("0000");
    	
    	Client client2 = new Client();
    	client2.setlastName("Allison");
    	client2.setBirthDate("24-02-2021");
    	client2.setEmail("alice@mail.com");
    	client2.setId(1);
    	client2.setPhoneNumber("1234567");
    	client2.setPassword("0000");
    	
    	Client client3 = new Client();
    	client3.setfirstName("Alice");
    	client3.setBirthDate("24-02-2021");
    	client3.setEmail("alice@mail.com");
    	client3.setId(2);
    	client3.setPhoneNumber("1234567");
    	client3.setPassword("0000");
    	
    	Client client4 = new Client();
    	client4.setfirstName("Alice");
    	client4.setlastName("Allison");
    	client4.setBirthDate("24-02-2021");
    	client4.setEmail(null);
    	client4.setId(3);
    	client4.setPhoneNumber("1234567");
    	client4.setPassword("0000");
    	
    	Client client5 = new Client();
    	client5.setfirstName("Alice");
    	client5.setlastName("Allison");
    	client5.setBirthDate("24-02-2021");
    	client5.setEmail("alice1@mail.com");
    	client5.setPhoneNumber(null);
    	client5.setId(4);
    	client5.setPassword("0000");
    	
    	Client client6 = new Client();
    	client6.setfirstName("Alice");
    	client6.setlastName("Allison");
    	client6.setBirthDate("24-02-2021");
    	client6.setEmail(null);
    	client6.setPhoneNumber(null);
    	client6.setId(5);
    	client6.setPassword("0000");
    	
    	Client client7 = new Client();
    	client7.setfirstName("Alice");
    	client7.setlastName("Allison");
    	client7.setBirthDate("24-02-2021");
    	client7.setEmail("alice12@mail.com");
    	client7.setPhoneNumber("1234567");
    	client7.setId(5);
    	client7.setPassword("0000");
    	
    	Client client8 = new Client();
    	client8.setfirstName("Alice");
    	client8.setlastName("Allison");
    	client8.setBirthDate("24-02-2021");
    	client8.setEmail("alice@mail.com");
    	client8.setPhoneNumber("123456799");
    	client8.setId(5);
    	client8.setPassword("0000");
		
		ResponseObject response = company.CreateNewClient(client1);
		ResponseObject response1 = company.CreateNewClient(client2);
		ResponseObject response2 = company.CreateNewClient(client3);
		ResponseObject response3 = company.CreateNewClient(client4);
		ResponseObject response4 = company.CreateNewClient(client5);
		ResponseObject response5 = company.CreateNewClient(client6);
		ResponseObject response6 = company.CreateNewClient(client7);
		ResponseObject response7 = company.CreateNewClient(client8);
		
		assertTrue("client not created", response.getErrorCode() == 1000);
		assertTrue("client not created", response1.getErrorCode() == 1001);
		assertTrue("client not created", response2.getErrorCode() == 1001);
		assertTrue("client not created", response5.getErrorCode() == 1002);
		assertTrue("client not created", response3.getErrorCode() == 1002);
		assertTrue("client not created", response4.getErrorCode() == 1002);
		assertTrue("client not created", response6.getErrorCode() == 1003);
		assertTrue("client not created", response7.getErrorCode() == 1003);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetClientInt() throws Exception {
		client = new Client();
		company = LogisticsCompany.getInstance();
		client.setId(1);
		client.setfirstName("Alice");
		
		Client client1 = new Client("Alan", "Mansour", "21/04/2021", "Alan.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
		company.CreateNewClient(client);
		assertTrue("not the correct client", client.getId() == company.getClientDatabase().getClient(1).get(0).getId());
		
		
		
	}

	@Test (expected = NullPointerException.class)
	public void testGetClientStringString() {
		
		ClientDatabase registery = new ClientDatabase();
		
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client1 = new Client("Alan", "Mansour", "21/04/2021", "Alan.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		
		registery.setClients(clients);
		
		company = LogisticsCompany.getInstance();
		
		
		assertTrue("not the correct client", client1.getfirstName().equals(company.getClientDatabase().getClient("Alan", "Mansour").get(0).getfirstName()) & client.getlastName().equals(company.getClientDatabase().getClient("Alan", "Mansour").get(0).getlastName()));
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetClientString() {
		
	ClientDatabase registery = new ClientDatabase();
		
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client1 = new Client("LOOO", "DOOOOr", "21/04/2021", "LOOO.DOOO@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		
		registery.setClients(clients);
		
		company = LogisticsCompany.getInstance();
		
		
		assertTrue("not the correct client", client1.getfirstName().equals(company.getClientDatabase().getClient("LOOO.DOOO@mail.com").get(0).getfirstName()) & client.getlastName().equals(company.getClientDatabase().getClient("LOOO.DOOO@mail.com").get(0).getlastName()));
		
	}

}
