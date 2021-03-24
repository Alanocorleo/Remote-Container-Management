import clientsManagement.Client;
import clientsManagement.Registery;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import journeysManagement.Container;
import journeysManagement.Registration;
import journeysManagement.Record;

import static org.junit.Assert.*;



public class StepDefinition {
	
	Client newClient = new Client();
	Registery newRegister = new Registery();
	
	@Given("a new client with name {string}, born {int}, email {string} and phone number {int}")
	public void a_new_client_with_name_born_email_and_phone_number(String name, Integer BirthDate, String email, Integer PhoneNumber) {
	    // Write code here that turns the phrase above into concrete actions
	
	newClient.setName(name);
	newClient.setBirthDate(BirthDate);
	newClient.setEmail(email);
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

	
	
}
