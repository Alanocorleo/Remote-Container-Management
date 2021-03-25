import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import clientsManagement.Client;
import journeysManagement.Container;
import journeysManagement.Registration;
import journeysManagement.ResponseObject;
import journeysManagement.Record;

public class StepDefinitionM2 {

	Registration registration = new Registration(new Client());
	Container container = new Container();
	Record record = new Record();
	ResponseObject response;
	
	@Given("client-ID")
	public void clientId() {
		container.setOwner(registration.getClient().getId());
		assertTrue(String.valueOf(container.getOwner()).matches("\\d"));
		
	}

	@Given("origin {string}")
	public void origin(String origin) {
	    container.setOrigin(origin);
	    
	}

	@Given("destination {string}")
	public void destination(String destination) {
		container.setDestination(destination);
	}

	@Given("content-type {string}")
	public void content_type(String contentType) {
		container.setContentType(contentType);
	}

	@Given("company {string}")
	public void company(String company) {
		container.setCompany(company);
	}

	@When("registering")
	public void registering() {
		response = registration.register(container);
	}

	@Then("confirm the registration")
	public void create_a_registration() {
		assertEquals(response.getErrorMessage(), "Container has been registered");
		assertEquals(response.getErrorCode(), 010);
	}
	
	@Then("create a journey-ID {string}")
	public void create_a_journey_ID(String journeyID) {
		registration.createJourney();
		assertEquals(container.getJourneyID().substring(0,2), String.format("%c%c", container.getOrigin().charAt(0), container.getDestination().charAt(0)));
		assertTrue(container.getJourneyID().matches("[A-Z]{1}\\w{2}\\d{4}"));
	}

	@Then("put on record")
	public void put_on_record() { 
		record.put(container);
		assertEquals(record.getRecord().get(0), container);
	}
	
	@Then("system displays a message that entry information is missing")
	public void system_displays_a_message_that_entry_information_is_missing() {
		assertEquals(response.getErrorMessage(), "Some necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 110);
	}
	

}
