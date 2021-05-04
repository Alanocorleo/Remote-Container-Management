import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.LogisticsCompany;
import response.ResponseObject;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class StepDefinitionRegisterClients {
	
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
	
	//Scenario 1  A client is registering in the system with date of birth, full name and email, phone number
	
	@Given("a new client with name {string}")
	public void a_new_client_with_name(String name) {
		newClient.setfirstName("Alice");
		newClient.setlastName("Allison");
	}

	@Given("born {string}")
	public void born(String BirthDate) {
		newClient.setBirthDate(BirthDate);
	}

	@Given("with email {string}")
	public void with_email(String email) {
		newClient.setEmail(email);
	}

	@Given("with phone number {string}")
	public void with_phone_number(String PhoneNumber) {
		newClient.setPhoneNumber(PhoneNumber);
	}

	@When("the company registers the client")
	public void the_company_registers_the_client() throws Exception {
		logisticsCompany.cleanDataBase();
		logisticsCompany.CreateNewClient(newClient);
	}
	
	@Then("the client is registered in the system successfully")
	public void the_client_is_registered_in_the_system_successfully() {
	    assertTrue(logisticsCompany.getClientDatabase().ClientExists(newClient));
	}

	@Then("the client is assigned a unique company ID {int}")
	public void the_client_is_assigned_a_unique_company_ID(Integer id) {
		assertTrue(logisticsCompany.getClientDatabase().checkID(newClient));
	}
	
	//Scenario 2 Same client registers again
	
	@Given("a registered client")
	public void a_registered_client() throws Exception {
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(registeredClient);
	}

	@When("the registered client tries to register again")
	public void the_registered_client_tries_to_register_again() throws Exception {
		response2 = logisticsCompany.CreateNewClient(registeredClient);
	}

	@Then("the client is not registered in the system again")
	public void the_client_is_not_registered_in_the_system_again() {
		assertTrue(response1.getErrorCode() == 90);
		assertTrue(response2.getErrorCode() == 903);
	}

	//scenario 3  Two client with same name but different contact info register
	
	@Given("an already registered client")
	public void an_already_registered_client() throws Exception {
		logisticsCompany.cleanDataBase();
		logisticsCompany.CreateNewClient(registeredClient);
	}
	
	@When("a new client registers with the same name but different contact info")
	public void a_new_client_registers_with_the_same_name_but_different_contact_info() throws Exception {
		logisticsCompany.CreateNewClient(clientWithSameNameasRegisteredClient);
	}
	
	@Then("the new client is registered in the system successfully")
	public void the_new_client_is_registered_in_the_system_successfully() {
		logisticsCompany.getClientDatabase().ClientExists(registeredClient);
		logisticsCompany.getClientDatabase().ClientExists(clientWithSameNameasRegisteredClient);
	}
	
	@Then("both clients have different unique company IDs")
	public void both_clients_have_different_unique_company_IDs() {
		assertTrue(logisticsCompany.getClientDatabase().checkID(registeredClient));
		assertTrue(logisticsCompany.getClientDatabase().checkID(clientWithSameNameasRegisteredClient)); 
	}
	
	//Scenario 4: Client tries to register without name
	
	@Given("a client without a name")
	public void a_client_without_a_name() {
	}

	@When("they try to regsiter without a name")
	public void they_try_to_regsiter_without_a_name() throws Exception {
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(newClient);
	}

	@Then("the client is not registered in the system")
	public void the_client_is_not_registered_in_the_system() {
	    assertTrue(response1.getErrorCode() == 900);
	}
	
	//scenario 5: Client tries to register with one contact info
	
	@Given("a client with all info except email")
	public void a_client_with_all_info_except_email() {
		clientWithoutEmail = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
		clientWithoutEmail.setEmail(null);
	}

	@When("they try to regsiter without an email")
	public void they_try_to_regsiter_without_an_email() throws Exception {
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(clientWithoutEmail);
	}

	@Then("the client without the email is not registered in the system successfully")
	public void the_client_without_the_email_is_not_registered_in_the_system_successfully() {
	    assertTrue(response1.getErrorCode() == 902);
	}
	
	//Scenario 6: Client tries to register with the other contact info
	
	@Given("a client with all info except phone number")
	public void a_client_with_all_info_except_phone_number() {
		clientWithoutPhoneNumber = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
		clientWithoutPhoneNumber.setPhoneNumber(null);
	}

	@When("they try to regsiter without an phone number")
	public void they_try_to_regsiter_without_an_phone_number() throws Exception {
		logisticsCompany.cleanDataBase();
		response1 = logisticsCompany.CreateNewClient(clientWithoutPhoneNumber);
	}

	@Then("the client without the phone number is registered in the system successfully")
	public void the_client_without_the_phone_number_is_registered_in_the_system_successfully() {
		assertTrue(response1.getErrorCode() == 902);
	}
}