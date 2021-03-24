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
	
	
	
	@Given("a new client with name {string}")
	public void a_new_client_with_name(String name) {
	    // Write code here that turns the phrase above into concrete actions
		newClient.setName(name);
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
		assertTrue(newRegistery.checkID(newClient));
	   
	}


	
	
}
