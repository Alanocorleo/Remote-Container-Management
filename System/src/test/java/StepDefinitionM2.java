import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import clientsManagement.Client;
import journeysManagement.Container;
import journeysManagement.Manager;
import journeysManagement.Registration;
import journeysManagement.ResponseObject;
import journeysManagement.Record;

public class StepDefinitionM2 {

	Container container;
	Client client;
	Registration registration;
	Manager manager;
	ResponseObject response;
	
	Record record = new Record();
	
	@Given("container")
	public void container() {
	   container = new Container();
	}
	
	@Given("client-ID")
	public void clientId() {
		client = new Client();
		container.setOwner(client.getId());
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
		registration = new Registration(client);
		response = registration.register(container);
	}

	@Then("get confirmation")
	public void get_confirmation() {
		assertEquals(response.getErrorMessage(), "Container has been registered");
		assertEquals(response.getErrorCode(), 010);
	}
	
	@Then("create journey-ID {string}")
	public void create_journey_ID(String journeyID) {
		registration.createJourneyID(record);
		assertEquals(registration.getJourneyID().substring(0,2), String.format("%c%c", journeyID.charAt(0), journeyID.charAt(1)));
		assertTrue(registration.getJourneyID().matches("[A-Z]{1}\\w{2}\\d{4}"));
	}

	@Then("put on record")
	public void put_on_record() { 
		registration.put(container, record);
		assertEquals(record.get().get(registration.getJourneyID()), container);
	}
	
	@Then("deny confirmation")
	public void deny_confirmation() {
		assertEquals(response.getErrorMessage(), "Some necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 110);
	}

	@Given("company manager {string}")
	public void company_manager(String company) {
		manager = new Manager(company);
	}
	
	@Given("journey-ID {string}")
	public void journey_ID(String string) {
	    
	}
	
	@Given("recorded container")
	public void recorded_container() {
		client = new Client();
		container = new Container();
		container.setOwner(client.getId());
		container.setOrigin("Copenhagen");
		container.setDestination("Oslo");
		container.setContentType("Fish");
		container.setCompany("Maersk");
		registration = new Registration(client);
		registration.register(container);
		registration.setJourneyID("CO00001");
		registration.put(container, record);
	}

	@When("updating journey {string} to position {string}")
	public void updating_to_position(String journeyID, String position) {
		response = manager.update(journeyID, position, record);
	}

	@Then("change position")
	public void change_position() {
		assertEquals(record.get().get("CO00001").getPosition(), "Gothenburg");
		assertEquals(response.getErrorMessage(), "Position has been updated");
		assertEquals(response.getErrorCode(), 070);
	}
	
	@Then("deny update")
	public void deny_update() {
		assertEquals(response.getErrorMessage(), "The journey was not found");
		assertEquals(response.getErrorCode(), 700);
	}

	

}
