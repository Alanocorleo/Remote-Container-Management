
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import io.cucumber.java.Before;
import management.Client;
import management.ClientDatabase;
import management.ContainerDatabase;
import management.LogisticsCompany;

public class ClientDatabaseTest {
	
	private Client client;
	private LogisticsCompany company;
	private ClientDatabase database;
	
	@Before
	public void createClient() {
		client = new Client();
		company = LogisticsCompany.getInstance();
		
	}

	@Test
	public void testGetClientsandSetClients() {
		
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		
		
		Client client1 = new Client("Alan", "Mansour", "21/04/2021", "Alan.Mansour@mail.com", "+999-999-99-998", "0000");
    	client1.setPassword("0000");
    	clients.add(client1);
		Client client2 = new Client("Javier" ,"Almendra", "21/04/2021", "Javier.Almendra@mail.com", "+999-999-99-999");
		client2.setId(100001);
		clients.add(client2);
		Client client3 = new Client("Kristyn" ,"Korboe", "21/04/2021", "Kristyn.Korboe@mail.com", "+999-999-99-999");
		client3.setId(100002);
		clients.add(client3);
		
		database.setClients(clients);
		
		assertTrue(clients.size() == database.getClients().size());
		
	}



	@Test
	public void testAllowedUpdate() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		
		
		Client client1 = new Client("JJ", "Mansour", "21/04/2021", "JJ.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		Client client2 = new Client("Roro" ,"Almendra", "21/04/2021", "Roro.Almendra@mail.com", "+999-999-99-999");
		client2.setId(100001);
		clients.add(client2);
		Client client3 = new Client("Lolo" ,"Korboe", "21/04/2021", "Lolo.Korboe@mail.com", "+999-999-99-999");
		client3.setId(100002);
		clients.add(client3);
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		assertTrue("not updates", database.allowedUpdate(100000, "Alan.Mansour1@mail.com"));
		assertFalse("updates", database.allowedUpdate(100000, "Roro.Almendra@mail.com"));
		assertFalse("phone number updates", database.allowedUpdate(100000, "+999-999-99-999"));
		
	}

	@Test
	public void testGetPassword() {
		
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		
		
		Client client1 = new Client("JJ", "Mansour", "21/04/2021", "JJ.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		Client client2 = new Client("Roro" ,"Almendra", "21/04/2021", "Roro.Almendra@mail.com", "+999-999-99-999");
		client2.setId(100001);
		client2.setPassword("0001");
		clients.add(client2);
		Client client3 = new Client("Lolo" ,"Korboe", "21/04/2021", "Lolo.Korboe@mail.com", "+999-999-99-999");
		client3.setId(100002);
		clients.add(client3);
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		assertTrue("not correct password", "0000".equals(database.getPassword("JJ.Mansour@mail.com")));
		assertFalse("correct password", "0000".equals(database.getPassword("Roro.Almendra@mail.com")));
		assertFalse("correct password", "0000".equals(database.getPassword("Roro111.Almendra@mail.com")));
		assertTrue(database.getClient(0).isEmpty()) ;
		assertFalse(database.getClient(100000).isEmpty()) ;
		
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetClientbyEmail() {
		
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		
		
		Client client1 = new Client("Yoyo", "Mansour", "21/04/2021", "Yoyo.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
	
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(client1.equals(database.getClient("Yoyo.Mansour@mail.com").get(0)));
		assertFalse(client1.equals(database.getClient("Boyo.Mansour@mail.com").get(0)));
		
	}

	@Test
	public void testLegalEmailToAddClient() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("Yoyo", "Mansour", "21/04/2021", "Yoyo.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
    	Client client2 = new Client("Gogo", "Mansour", "21/04/2021", "Yoyo.Mansour@mail.com", "+999-999-99-999");
    	client2.setId(100000);
    	client2.setPassword("0000");
    	clients.add(client2);
	
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		assertFalse(database.legalEmailToAddClient(client2));
		
		
	}

	@Test 
	public void testLegalPhoneNumberToAddClient() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("Dodo", "Mansour", "21/04/2021", "DoDo.Mansour@mail.com", "+999-999-99-99800000");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
	
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertFalse(database.legalPhoneNumberToAddClient(client1));
	}

	@Test
	public void testClientExists() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("Gogo", "Mansour", "21/04/2021", "Gogo.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
    	Client c = new Client();
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(database.ClientExists(client1));
		assertFalse(database.ClientExists(c));
	}

	@Test
	public void testCheckID() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("Fogo", "Mansour", "21/04/2021", "Fogo.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
    	Client c = new Client();
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(database.checkID(client1));
		assertFalse(database.checkID(c));
	}

	

	@Test
	public void testCheckUpdatedInfo() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("Fofo", "Mansour", "21/04/2021", "Fofo.Mansour@mail.com", "+999-999-99-998");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
		database.setClients(clients);
		try {
			database.push();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(database.checkUpdatedInfo("Fofo", "Mansour", "21/04/2021", "Fofo.Mansour@mail.com", "+999-999-99-998"));
		assertFalse(database.checkUpdatedInfo("Itsyaboi", "Lolo", "21/04/2021", "Fofo1.Mansour@mail.com", "+999-999-99-998"));
		assertFalse(database.checkUpdatedInfo("Fofo", "Mansourooo", "21/04/2021", "Fofo.Mansour@mail.com", "+999-999-99-998"));
		assertFalse(database.checkUpdatedInfo("Fofo", "Mansour", "21/04/2021111", "Fofo.Mansour@mail.com", "+999-999-99-998"));
		assertFalse(database.checkUpdatedInfo("Fofo", "Mansour", "21/04/2021", "Fofo1111.Mansour@mail.com", "+999-999-99-998"));
		assertFalse(database.checkUpdatedInfo("Fofo", "Mansour", "21/04/2021", "Fofo.Mansour@mail.com", "12345678"));
	}



	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetClientStringString() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("JoJo", "Mansour", "21/04/2021", "Jojo.Mansour@mail.com", "+999-999-99-990");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
    	Client c = new Client();
		database.setClients(clients);
	
		
		assertTrue(client1.equals(database.getClient("JoJo",  "Mansour").get(0)));
		assertFalse(c.equals(database.getClient("JoJo1",  "Mansour").get(0)));
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetClientString() {
		ArrayList<Client> clients = new ArrayList<Client>();
		database = new ClientDatabase();
		Client client1 = new Client("BloBlo", "Mansour", "21/04/2021", "BloBlo.Mansour@mail.com", "+999-999-99-997");
    	client1.setId(100000);
    	client1.setPassword("0000");
    	clients.add(client1);
    	Client c = new Client();
		database.setClients(clients);
	
		
		assertTrue(client1.equals(database.getClient("BloBlo.Mansour@mail.com").get(0)));
		assertFalse(c.equals(database.getClient("BloBlo11.Mansour@mail.com").get(0)));
	}

}
