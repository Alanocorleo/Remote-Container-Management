import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.LogisticsCompany;
import response.ResponseObject;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class StepDefinitionContactClient {
	
	LogisticsCompany logisticsCompany = LogisticsCompany.getInstance();
	Client newClient = new Client();
	Client registeredClient = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	Client registeredClient2 = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	Client clientWithSameNameasRegisteredClient = new Client("Alice", "Allison", "999999","AliceAllison12@gmail.com" , "12345679");
	Client clientWithoutEmail;
	Client clientWithoutPhoneNumber;
	Client unregisteredClient;
	ArrayList <Client> theReturnedClientList;
	LogisticsCompany logisticscompany2;
	ResponseObject response1;
	ResponseObject response2;
	
	// Company get info of client feature:
		
	// Scenario 1: Contacting registered client by unique id

	@Given("registered client2")
	public void registered_client() throws Exception {
		logisticsCompany.cleanDataBase();
		logisticsCompany.CreateNewClient(registeredClient2);
	}

	@When("the company searches for the client by ID")
	public void the_company_searches_for_the_client_by_ID() {
		theReturnedClientList = logisticsCompany.getClientDatabase().getClient(1);
	}
	
	@Then("the company gets all the info of the client")
	public void the_company_gets_all_the_info_of_the_client() {
		for (Client theReturnedClient : theReturnedClientList) {
		assertTrue(theReturnedClient.getfirstName() == registeredClient2.getfirstName());
		assertTrue(theReturnedClient.getlastName() == registeredClient2.getlastName());
		assertTrue(theReturnedClient.getBirthDate() == registeredClient2.getBirthDate());
		assertTrue(theReturnedClient.getEmail() == registeredClient2.getEmail());
		assertTrue(theReturnedClient.getPhoneNumber() == registeredClient2.getPhoneNumber());
		assertTrue(theReturnedClient.getId() == registeredClient2.getId());
		}
	}


	// Scenario 2: Contacting registered client by name

	@Given("atleast {int} registered clients with the same name")
	public void atleast_registered_clients_with_the_same_name(Integer int1) throws Exception {
		logisticsCompany.cleanDataBase();
		logisticsCompany.CreateNewClient(registeredClient);
		logisticsCompany.CreateNewClient(registeredClient2);
	}
	
	@When("the company searches for the client by name")
	public void the_company_searches_for_the_client_by_name() {
		theReturnedClientList =   logisticsCompany.getClientDatabase().getClient("Alice", "Allison");  
	}
	
	@Then("the company gets all the info of the client of both clients")
	public void the_company_gets_all_the_info_of_the_client_of_both_clients() {
		for (Client theReturnedClient : theReturnedClientList) {
			assertTrue(theReturnedClient.getfirstName() == registeredClient.getfirstName());
			assertTrue(theReturnedClient.getlastName() == registeredClient2.getlastName());
			assertTrue(theReturnedClient.getfirstName() == registeredClient2.getfirstName());
			assertTrue(theReturnedClient.getlastName() == registeredClient.getlastName());
		}
	}
	
	// Scenario 3:  Contacting client by id that doesn't exists
	
	@Given("a registery")
	public void a_registery() throws Exception {
		 logisticsCompany.cleanDataBase();
		 logisticsCompany.CreateNewClient(registeredClient);
		 logisticsCompany.CreateNewClient(registeredClient2);
	}

	@When("the company searches for the client by the non existing ID")
	public void the_company_searches_for_the_client_by_the_non_existing_ID() {
		 theReturnedClientList = logisticsCompany.getClientDatabase().getClient(69); 
	}

	@Then("the company does not get any info")
	public void the_company_does_not_get_any_info() {
		assertTrue(theReturnedClientList.isEmpty());
		
	}

	// Scenario 4: Contacting client by name that doesn't exists

	@When("the company searches for the client by the non existing name")
	public void the_company_searches_for_the_client_by_the_non_existing_name() {
		theReturnedClientList = logisticsCompany.getClientDatabase().getClient("Dragon","Slayer");
	}
	
	// Scenario 5: Contacting registered client by email
	
	@When("the company searches for the client by email")
	public void the_company_searches_for_the_client_by_email() {
		theReturnedClientList = logisticsCompany.getClientDatabase().getClient("AliceAllison@gmail.com");
	}

}