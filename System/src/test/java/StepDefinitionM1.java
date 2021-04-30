import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.LogisticsCompany;
import response.ResponseObject;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class StepDefinitionM1 {
	
	LogisticsCompany logisticsCompany = LogisticsCompany.getInstance();
	Client newClient = new Client();
	Client registeredClient = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	Client clientWithSameNameasRegisteredClient = new Client("Alice", "Allison", "999999","AliceAllison12@gmail.com" , "12345679");
	Client clientWithoutEmail;
	Client clientWithoutPhoneNumber;
	Client registeredClient2 = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	LogisticsCompany logisticscompany2;
	Client unregisteredClient;
	ArrayList <Client> theReturnedClientList;
	ResponseObject response1;
	ResponseObject response2;
	
	Client c1;
	Client c2;
	Client c3;
	Client c4;
	Client c5;
	//M1 feature 1 scenario 2
	
	
	@Given("a new client with name {string}")
	public void a_new_client_with_name(String name) {
	    // Write code here that turns the phrase above into concrete actions
		
		newClient.setfirstName("Alice");
		newClient.setlastName("Allison");

	}

	@Given("born {string}")
	public void born(String BirthDate) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setBirthDate(BirthDate);
	}

	@Given("with email {string}")
	public void with_email(String email) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setEmail(email);
	}

	@Given("with phone number {string}")
	public void with_phone_number(String PhoneNumber) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setPhoneNumber(PhoneNumber);
	}

	@When("the company registers the client")
	public void the_company_registers_the_client() throws Exception {
		
		logisticsCompany.cleanDataBase();
		logisticsCompany.CreateNewClient(newClient);
	
	    // Write code here that turns the phrase above into concrete actions
	 
	}
	@Then("the client is registered in the system successfully")
	public void the_client_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    
	    assertTrue(logisticsCompany.getClientDatabase().ClientExists(newClient));
	}

	@Then("the client is assigned a unique company ID {int}")
	public void the_client_is_assigned_a_unique_company_ID(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(logisticsCompany.getClientDatabase().checkID(newClient));
	   
	}
	//M1 feature 1 scenario 2
	@Given("a registered client")
	public void a_registered_client() throws Exception {
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(registeredClient);


	    // Write code here that turns the phrase above into concrete actions
		
	}

	@When("the registered client tries to register again")
	public void the_registered_client_tries_to_register_again() throws Exception {
		response2 = logisticsCompany.CreateNewClient(registeredClient);
	    
	}

	@Then("the client is not registered in the system again")
	public void the_client_is_not_registered_in_the_system_again() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(response1.getErrorCode() == 1000);
		assertTrue(response2.getErrorCode() == 1003);

	}

	//scenario 3
	

	@Given("an already registered client")
	public void an_already_registered_client() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();
		logisticsCompany.CreateNewClient(registeredClient);
	}
	
	@When("a new client registers with the same name but different contact info")
	public void a_new_client_registers_with_the_same_name_but_different_contact_info() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.CreateNewClient(clientWithSameNameasRegisteredClient);
		
	}
	
	@Then("the new client is registered in the system successfully")
	public void the_new_client_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.getClientDatabase().ClientExists(registeredClient);
		logisticsCompany.getClientDatabase().ClientExists(clientWithSameNameasRegisteredClient);
	   
	}
	
	@Then("both clients have different unique company IDs")
	public void both_clients_have_different_unique_company_IDs() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(logisticsCompany.getClientDatabase().checkID(registeredClient));
		assertTrue(logisticsCompany.getClientDatabase().checkID(clientWithSameNameasRegisteredClient)); 
	}
	
	//Scenario 4:
	
	@Given("a client without a name")
	public void a_client_without_a_name() {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("they try to regsiter without a name")
	public void they_try_to_regsiter_without_a_name() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(newClient);
	}

	@Then("then the client is not registered in the system")
	public void then_the_client_is_not_registered_in_the_system() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(response1.getErrorCode() == 1001);
	}
	//scenario 5:
	
	@Given("a client with all info except email")
	public void a_client_with_all_info_except_email() {
	    // Write code here that turns the phrase above into concrete actions
		clientWithoutEmail = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
		clientWithoutEmail.setEmail(null);
	}

	@When("they try to regsiter without an email")
	public void they_try_to_regsiter_without_an_email() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(clientWithoutEmail);
	}

	@Then("then the client is registered in the system successfully")
	public void then_the_client_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(response1.getErrorCode() == 1002);
	}
//sceanrio 6:
	
	@Given("a client with all info except phone number")
	public void a_client_with_all_info_except_phone_number() {
	    // Write code here that turns the phrase above into concrete actions
		clientWithoutPhoneNumber = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
		clientWithoutPhoneNumber.setPhoneNumber(null);
	}

	@When("they try to regsiter without an phone number")
	public void they_try_to_regsiter_without_an_phone_number() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(clientWithoutPhoneNumber);

	}

	@Then("then the client without the phone numver is registered in the system successfully")
	public void then_the_client_without_the_phone_numver_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		 assertTrue(response1.getErrorCode() == 1002);
	    
	}
	
	//client update feature
	//scenario 1:
	
	@Given("registered client")
	public void registered_client() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();

		logisticsCompany.CreateNewClient(registeredClient2);

	}

	@When("the client wants to update all their information except their name and birth date")
	public void the_client_wants_to_update_all_their_information_except_their_name_and_birth_date() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    response1 = registeredClient2.updateInfo("Alice", "Allison", "01012000", "AliceAllison12@gmail.com", "12345679", logisticsCompany.getClientDatabase());
	}

	@Then("the informtion is updated successfully")
	public void the_informtion_is_updated_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(response1.getErrorCode()==1004);
	    
	}
	//scenario 2:
	
	@Given("unregistered client")
	public void unregistered_client() {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();

		unregisteredClient = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	    
	}

	@When("the person wants to update all their information except their name and birth date")
	public void the_person_wants_to_update_all_their_information_except_their_name_and_birth_date() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		unregisteredClient.updateInfo("Alice","Allison","01012000" ,"AliceAllison12@gmail.com", "12345679", logisticsCompany.getClientDatabase());
	   	}

	@Then("the informtion is not updated successfully")
	public void the_informtion_is_not_updated_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	   assertFalse(logisticsCompany.getClientDatabase().checkUpdatedInfo("Alice", "Allison", "01012000", "AliceAllison12@gmail.com", "12345679"));
	}
	
	//scenario 3:
	
	@Given("a phone number that exists in the system")
	public void a_phone_number_that_exists_in_the_system() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();

		logisticsCompany.CreateNewClient(new Client("Alice", "Bobbinson","01012000","AliceAllison12@gmail.com", "12345679"));
		logisticsCompany.CreateNewClient(registeredClient2);

	    
	}

	@When("the client wants to update all their contact phone number to already existing phone number")
	public void the_client_wants_to_update_all_their_contact_phone_number_to_already_existing_phone_number() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		response1 = registeredClient2.updateInfo("Alice", "Allison","01012000", "AliceAllison@gmail.com", "12345679",  logisticsCompany.getClientDatabase());
	}

	@Then("the informtion is not updated")
	public void the_informtion_is_not_updated() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(response1.getErrorCode()== 1006);
	    
	}
	
	//scenario 4:
	
	@Given("a email that exists in the system")
	public void a_email_that_exists_in_the_system() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();

		logisticsCompany.CreateNewClient(new Client("Alice", "Bobbinson","01012000" ,"AliceAllison12@gmail.com", "12345679"));
		logisticsCompany.CreateNewClient(registeredClient2);

	}

	@When("the client wants to update all their contact phone number to already existing email")
	public void the_client_wants_to_update_all_their_contact_phone_number_to_already_existing_email() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		response1 = registeredClient2.updateInfo("Alice", "Allison","01012000" ,"AliceAllison12@gmail.com", "12345678",  logisticsCompany.getClientDatabase());
	}

	@Then("the informtion is not updated version {int}")
	public void the_informtion_is_not_updated_version(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(response1.getErrorCode()==1005);
	}

// company get info of client feature:
	
	//scenario 1:
	

	@When("the company searches for the client by id")
	public void the_company_searches_for_the_client_by_id() {
	    // Write code here that turns the phrase above into concrete actions
		
		theReturnedClientList = logisticsCompany.getClient(1);
	  
	}
	
	@Then("the company gets all the info of the client")
	public void the_company_gets_all_the_info_of_the_client() {
		
		for (Client theReturnedClient : theReturnedClientList) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(theReturnedClient.getfirstName() == registeredClient2.getfirstName());
		assertTrue(theReturnedClient.getlastName() == registeredClient2.getlastName());
		assertTrue(theReturnedClient.getBirthDate() == registeredClient2.getBirthDate());
		assertTrue(theReturnedClient.getEmail() == registeredClient2.getEmail());
		assertTrue(theReturnedClient.getPhoneNumber() == registeredClient2.getPhoneNumber());
		assertTrue(theReturnedClient.getId() == registeredClient2.getId());
	}
	}


//scenario 2:

	@Given("atleast {int} registered clients with the same name")
	public void atleast_registered_clients_with_the_same_name(Integer int1) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();

		logisticsCompany.CreateNewClient(registeredClient);
		logisticsCompany.CreateNewClient(registeredClient2);

	}
	
	@When("the company searches for the client by name")
	public void the_company_searches_for_the_client_by_name() {
	    // Write code here that turns the phrase above into concrete actions
		theReturnedClientList =   logisticsCompany.getClient("Alice", "Allison");
	   
	    
	}
	
	@Then("the company gets all the info of the client of both clients")
	public void the_company_gets_all_the_info_of_the_client_of_both_clients() {
	    // Write code here that turns the phrase above into concrete actions
		for (Client theReturnedClient : theReturnedClientList) {
		    // Write code here that turns the phrase above into concrete actions
			assertTrue(theReturnedClient.getfirstName() == registeredClient.getfirstName());
			assertTrue(theReturnedClient.getlastName() == registeredClient2.getlastName());
			assertTrue(theReturnedClient.getfirstName() == registeredClient2.getfirstName());
			assertTrue(theReturnedClient.getlastName() == registeredClient.getlastName());
		
		}
	    
	}
	
	//scenario 3:
	@Given("a registery")
	public void a_registery() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		 logisticsCompany.cleanDataBase();

		 logisticsCompany.CreateNewClient(registeredClient);
		 logisticsCompany.CreateNewClient(registeredClient2);

	 
	}

	@When("the company searches for the client by the non existing id")
	public void the_company_searches_for_the_client_by_the_non_existing_id() {
	    // Write code here that turns the phrase above into concrete actions
		 theReturnedClientList = logisticsCompany.getClient(69);
	    
	}

	@Then("the company does not get any info")
	public void the_company_does_not_get_any_info() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(theReturnedClientList.isEmpty());
		
	}

	
	//scenario 4:

	

	@When("the company searches for the client by the non existing name")
	public void the_company_searches_for_the_client_by_the_non_existing_name() {
	    // Write code here that turns the phrase above into concrete actions
		theReturnedClientList = logisticsCompany.getClient("DragonSlayer69");
		
	}
	///Company stores info to the database and gets it back from the database

	@Given("A logistics company")
	public void a_logistics_company() {
		try {
			logisticsCompany.getClientDatabase().produce();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("five registered clients")
	public void five_registered_clients() {
	    // Write code here that turns the phrase above into concrete actions
		c1= new Client("Alice", "Allison","999999" ,"AliceAllison1@gmail.com", "12345678");
		c2 = new Client("Alice", "Allison","999999","AliceAllison2@gmail.com", "12345678");
		c3 = new Client("Alice", "Allison","999999" ,"AliceAllison3@gmail.com", "12345678");
		c4 = new Client("Alice", "Allison","999999" ,"AliceAllison4@gmail.com", "12345678");
		c5 = new Client("Alice" ,"Allison","999999" ,"AliceAllison5@gmail.com", "12345678");
	}
	
	@When("the company addes the clients")
	public void the_company_addes_the_clients() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticsCompany.cleanDataBase();

		logisticsCompany.CreateNewClient(c1);
		logisticsCompany.CreateNewClient(c2);
		logisticsCompany.CreateNewClient(c3);
		logisticsCompany.CreateNewClient(c4);
		logisticsCompany.CreateNewClient(c5);
	}
	
	@Then("The clients should be registered in the database")
	public void the_clients_should_be_registered_in_the_database() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Given("a logistics company that just joins the system")
	public void a_logistics_company_that_just_joins_the_system() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		c1= new Client("Alice", "Allison","999999" ,"AliceAllison1@gmail.com", "12345678");
		c2 = new Client("Alice", "Allison","999999","AliceAllison2@gmail.com", "22345678");
		c3 = new Client("Alice", "Allison","999999" ,"AliceAllison3@gmail.com", "32345678");
		c4 = new Client("Alice", "Allison","999999" ,"AliceAllison4@gmail.com", "42345678");
		c5 = new Client("Alice" ,"Allison","999999" ,"AliceAllison5@gmail.com", "52345678");
		logisticsCompany.cleanDataBase();

		logisticsCompany.CreateNewClient(c1);
		logisticsCompany.CreateNewClient(c2);
		logisticsCompany.CreateNewClient(c3);
		logisticsCompany.CreateNewClient(c4);
		logisticsCompany.CreateNewClient(c5);
	    logisticscompany2 = LogisticsCompany.getInstance();

	}
	
	@When("the company searches for client registered during the previous session")
	public void the_company_searches_for_client_registered_during_the_previous_session() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Then("the company has access to all previously added clients")
	public void the_company_has_access_to_all_previously_added_clients() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		logisticscompany2.getClientDatabase().print();
	    
	}
	
}
	

