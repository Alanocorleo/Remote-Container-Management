import clientsManagement.Client;
import clientsManagement.Registery;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.ArrayList;



public class StepDefinitionM1 {
	
	Client newClient = new Client();
	Registery newRegister = new Registery();
	Client registeredClient = new Client("Alice Allison",01012000 ,"AliceAllison@gmail.com", 12345678);
	Client clientWithSameNameasRegisteredClient = new Client("Alice Allison", 01012001,"AliceAllison12@gmail.com" , 12345679);
	Client clientWithoutEmail;
	Client clientWithoutPhoneNumber;
	Client registeredClient2 = new Client("Alice Allison",01012000 ,"AliceAllison@gmail.com", 12345678, "Bobby Blue" , "BORDVEJ 26");
	Client unregisteredClient;
	ArrayList <Client> theReturnedClientList;


	//M1 feature 1 scenario 2

	
	@Given("a new client with name {string}")
	public void a_new_client_with_name(String name) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setName("Alice Allison");
	}

	@Given("born {int}")
	public void born(Integer BirthDate) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setBirthDate(BirthDate);
	}

	@Given("with email {string}")
	public void with_email(String email) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setEmail(email);
	}

	@Given("with phone number {int}")
	public void with_phone_number(Integer PhoneNumber) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setPhoneNumber(PhoneNumber);
	}

	@When("the company registers the client")
	public void the_company_registers_the_client() {
		
	
	
	newRegister.addClient(newClient);
	
	    // Write code here that turns the phrase above into concrete actions
	 
	}
	@Then("the client is registered in the system successfully")
	public void the_client_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    
	    assertTrue(newRegister.ClientExists(newClient));
	}

	


	@Then("the client is assigned a unique company ID {int}")
	public void the_client_is_assigned_a_unique_company_ID(Integer id) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(newRegister.checkID(newClient));
	   
	}
	//M1 feature 1 scenario 2
	@Given("a registered client")
	public void a_registered_client() {
		newRegister.addClient(registeredClient);

	    // Write code here that turns the phrase above into concrete actions
		
	}

	@When("the registered client tries to register again")
	public void the_registered_client_tries_to_register_again() {
		newRegister.addClient(registeredClient);
	    
	}

	@Then("the client is not registered in the system again")
	public void the_client_is_not_registered_in_the_system_again() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(newRegister.checkContactInfoOfClients(registeredClient) == 1);

	}

	//scenario 3
	

	@Given("an already registered client")
	public void an_already_registered_client() {
	    // Write code here that turns the phrase above into concrete actions
	    newRegister.addClient(registeredClient);
	}
	
	@When("a new client registers with the same name but different contact info")
	public void a_new_client_registers_with_the_same_name_but_different_contact_info() {
	    // Write code here that turns the phrase above into concrete actions
		newRegister.addClient(clientWithSameNameasRegisteredClient);
	}
	
	@Then("the new client is registered in the system successfully")
	public void the_new_client_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		newRegister.ClientExists(registeredClient);
		newRegister.ClientExists(clientWithSameNameasRegisteredClient);
	   
	}
	
	@Then("both clients have different unique company IDs")
	public void both_clients_have_different_unique_company_IDs() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(newRegister.checkID(registeredClient));
		assertTrue(newRegister.checkID(clientWithSameNameasRegisteredClient));
	    
	}
	
	//Scenario 4:
	
	@Given("a client without a name")
	public void a_client_without_a_name() {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@When("they try to regsiter without a name")
	public void they_try_to_regsiter_without_a_name() {
	    // Write code here that turns the phrase above into concrete actions
	    newRegister.addClient(newClient);
	}

	@Then("then the client is not registered in the system")
	public void then_the_client_is_not_registered_in_the_system() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(newRegister.checkInstancesofClientinRegistery(newClient) == 0);
	}
	//scenario 5:
	
	@Given("a client with all info except email")
	public void a_client_with_all_info_except_email() {
	    // Write code here that turns the phrase above into concrete actions
		clientWithoutEmail = new Client("Alice Allison", 01012001 , 1234578);
		
		
		
	   
	}

	@When("they try to regsiter without an email")
	public void they_try_to_regsiter_without_an_email() {
	    // Write code here that turns the phrase above into concrete actions
		newRegister.addClient(clientWithoutEmail);
	}

	@Then("then the client is registered in the system successfully")
	public void then_the_client_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(newRegister.checkInstancesofClientinRegistery(clientWithoutEmail) == 1);
	}
//sceanrio 6:
	
	@Given("a client with all info except phone number")
	public void a_client_with_all_info_except_phone_number() {
	    // Write code here that turns the phrase above into concrete actions
		clientWithoutPhoneNumber = new Client("Alice Allison", 01012001, "AliceAllison12@gmail.com");
	}

	@When("they try to regsiter without an phone number")
	public void they_try_to_regsiter_without_an_phone_number() {
	    // Write code here that turns the phrase above into concrete actions
		newRegister.addClient(clientWithoutPhoneNumber);
	    
	}

	@Then("then the client without the phone numver is registered in the system successfully")
	public void then_the_client_without_the_phone_numver_is_registered_in_the_system_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		 assertTrue(newRegister.checkInstancesofClientinRegistery(clientWithoutPhoneNumber) == 1);
	    
	}
	
	//client update feature
	//scenario 1:
	
	@Given("registered client")
	public void registered_client() {
	    // Write code here that turns the phrase above into concrete actions
	    newRegister.addClient(registeredClient2);
	}

	@When("the client wants to update all their information except their name and birth date")
	public void the_client_wants_to_update_all_their_information_except_their_name_and_birth_date() {
	    // Write code here that turns the phrase above into concrete actions
	    registeredClient2.update("AliceAllison12@gmail.com", 12345679, "Bobby Blue Moon" , "BORDVEJ 38", newRegister);
	}

	@Then("the informtion is updated successfully")
	public void the_informtion_is_updated_successfully() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(newRegister.checkUpdatedInfo("Alice Allison", 01012000, "AliceAllison12@gmail.com", 12345679, "Bobby Blue Moon" , "BORDVEJ 38"));
	    
	}
	//scenario 2:
	
	@Given("unregistered client")
	public void unregistered_client() {
	    // Write code here that turns the phrase above into concrete actions
		unregisteredClient = new Client("Alice Allison",01012000 ,"AliceAllison@gmail.com", 12345678, "Bobby Blue" , "BORDVEJ 26");
	    
	}

	@When("the person wants to update all their information except their name and birth date")
	public void the_person_wants_to_update_all_their_information_except_their_name_and_birth_date() {
	    // Write code here that turns the phrase above into concrete actions
		unregisteredClient.update("AliceAllison12@gmail.com", 12345679, "Bobby Blue Moon" , "BORDVEJ 38",  newRegister);
	   	}

	@Then("the informtion is not updated successfully")
	public void the_informtion_is_not_updated_successfully() {
	    // Write code here that turns the phrase above into concrete actions
	   assertFalse(newRegister.checkUpdatedInfo("Alice Allison", 01012000, "AliceAllison12@gmail.com", 12345679, "Bobby Blue Moon" , "BORDVEJ 38"));
	}
	
	//scenario 3:
	
	@Given("a phone number that exists in the system")
	public void a_phone_number_that_exists_in_the_system() {
	    // Write code here that turns the phrase above into concrete actions
		newRegister.addClient(new Client("Alice Bobbinson",01012000 ,"AliceAllison12@gmail.com", 12345679, "Bobby Blue" , "BORDVEJ 26"));
	    
	}

	@When("the client wants to update all their contact phone number to already existing phone number")
	public void the_client_wants_to_update_all_their_contact_phone_number_to_already_existing_phone_number() {
	    // Write code here that turns the phrase above into concrete actions
		registeredClient2.update("AliceAllison@gmail.com", 12345679, "Bobby Blue Moon" , "BORDVEJ 38",  newRegister);
	}

	@Then("the informtion is not updated")
	public void the_informtion_is_not_updated() {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(newRegister.checkUpdatedInfo("Alice Allison",01012000 ,"AliceAllison@gmail.com", 12345679, "Bobby Blue Moon" , "BORDVEJ 38"));
	    
	}
	
	//scenario 4:
	
	@Given("a email that exists in the system")
	public void a_email_that_exists_in_the_system() {
	    // Write code here that turns the phrase above into concrete actions
		newRegister.addClient(new Client("Alice Bobbinson",01012000 ,"AliceAllison12@gmail.com", 12345679, "Bobby Blue" , "BORDVEJ 26"));
	}

	@When("the client wants to update all their contact phone number to already existing email")
	public void the_client_wants_to_update_all_their_contact_phone_number_to_already_existing_email() {
	    // Write code here that turns the phrase above into concrete actions
		registeredClient2.update("AliceAllison12@gmail.com", 12345678, "Bobby Blue Moon" , "BORDVEJ 38",  newRegister);
	}

	@Then("the informtion is not updated version {int}")
	public void the_informtion_is_not_updated_version(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(newRegister.checkUpdatedInfo("Alice Allison",01012000 ,"AliceAllison12@gmail.com", 12345678, "Bobby Blue Moon " , "BORDVEJ 38"));
	}

	

// company get info of client feature:
	
	//scenario 1:
	

	@When("the company searches for the client by id")
	public void the_company_searches_for_the_client_by_id() {
	    // Write code here that turns the phrase above into concrete actions
		 theReturnedClientList = newRegister.getInfoClientbyId(1);
	  
	}
	
	@Then("the company gets all the info of the client")
	public void the_company_gets_all_the_info_of_the_client() {
		
		for (Client theReturnedClient : theReturnedClientList) {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(theReturnedClient.getName() == registeredClient2.getName());
		assertTrue(theReturnedClient.getAddress() == registeredClient2.getAddress());
		assertTrue(theReturnedClient.getBirthDate() == registeredClient2.getBirthDate());
		assertTrue(theReturnedClient.getReferencePerson() == registeredClient2.getReferencePerson());
		assertTrue(theReturnedClient.getEmail() == registeredClient2.getEmail());
		assertTrue(theReturnedClient.getPhoneNumber() == registeredClient2.getPhoneNumber());
		assertTrue(theReturnedClient.getId() == registeredClient2.getId());
	}
	}


//scenario 2:

	@Given("atleast {int} registered clients with the same name")
	public void atleast_registered_clients_with_the_same_name(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    newRegister.addClient(registeredClient);
	    newRegister.addClient(registeredClient2);
	}
	
	@When("the company searches for the client by name")
	public void the_company_searches_for_the_client_by_name() {
	    // Write code here that turns the phrase above into concrete actions
		theReturnedClientList =   newRegister.getInfoClientbyName("Alice Allison");
	   
	    
	}
	
	@Then("the company gets all the info of the client of both clients")
	public void the_company_gets_all_the_info_of_the_client_of_both_clients() {
	    // Write code here that turns the phrase above into concrete actions
		for (Client theReturnedClient : theReturnedClientList) {
		    // Write code here that turns the phrase above into concrete actions
			assertTrue(theReturnedClient.getName() == registeredClient.getName());
			assertTrue(theReturnedClient.getName() == registeredClient2.getName());
		
		}
	    
	}
	
	//scenario 3:
	@Given("a registery")
	public void a_registery() {
	    // Write code here that turns the phrase above into concrete actions
		 newRegister.addClient(registeredClient);
		 newRegister.addClient(registeredClient2);
	 
	}

	@When("the company searches for the client by the non existing id")
	public void the_company_searches_for_the_client_by_the_non_existing_id() {
	    // Write code here that turns the phrase above into concrete actions
		 theReturnedClientList = newRegister.getInfoClientbyId(69);
	    
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
		theReturnedClientList = newRegister.getInfoClientbyName("DragonSlayer69");
	}

	



	}
	

