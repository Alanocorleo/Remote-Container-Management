import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import clientsManagement.Client;
import journeysManagement.Container;
import journeysManagement.Registration;
import journeysManagement.Record;
import journeysManagement.Manager;
import journeysManagement.Finder;
import journeysManagement.ResponseObject;

public class StepDefinitionM2 {

	Container container;
	Client client;
	Registration registration;
	Manager manager;
	Finder finder;
	ResponseObject response;
	
	Record record = new Record();
	
	@Given("client")
	public void client() {
		client = new Client();
		finder = new Finder(client);
		
	}
	
	@Given("container")
	public void container() {
	   container = new Container();
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

	@Then("confirm registration")
	public void confirm_registration() {
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
		registration.upload(record);
		assertEquals(record.getRecord().get(registration.getJourneyID()), container);
	}
	
	@Then("deny registration")
	public void deny_registration() {
		assertEquals(response.getErrorMessage(), "Some necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 110);
	}

	@Given("company manager {string}")
	public void company_manager(String company) {
		manager = new Manager(company);
	}
	
	@Given("recorded journey")
	public void recorded_journey() {
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
		registration.upload(record);
	}

	@When("updating containers current position of journey {string} to {string}")
	public void updating_containers_current_position_of_journey_to(String journeyID, String position) {
		response = manager.updatePosition(journeyID, position, record);
	}

	@Then("change position")
	public void change_position() {
		assertEquals(record.getRecord().get("CO00001").getPosition(), "Gothenburg");
		assertEquals(response.getErrorMessage(), "Position has been updated");
		assertEquals(response.getErrorCode(), 070);
	}
	
	@Then("deny update")
	public void deny_update() {
		assertEquals(response.getErrorMessage(), "Journey was not found");
		assertEquals(response.getErrorCode(), 700);
	}
	
	@When("completing journey {string}")
	public void completing_journey(String journeyID) {
		response = manager.completeJourney(journeyID, record);
	    
	}

	@Then("remove from record")
	public void remove_from_record() {
		assertEquals(record.getRecord().get("CO00001"), null);
		assertEquals(response.getErrorMessage(), "Journey has been completed and succesfully removed from the record");
		assertEquals(response.getErrorCode(), 050);
	}

	@Then("deny removal")
	public void deny_removal() {
		assertEquals(response.getErrorMessage(), "Journey was not found");
		assertEquals(response.getErrorCode(), 700);
	}
	
	@Given("recorded journeys")
	public void recorded_journeys() {
		container = new Container();
		container.setOwner(client.getId());
		container.setOrigin("Copenhagen");
		container.setDestination("Oslo");
		container.setContentType("Fish");
		container.setCompany("Maersk");
		registration = new Registration(client);
		registration.register(container);
		registration.setJourneyID("CO00001");
		registration.upload(record);
		registration.setJourneyID("CO00002");
		registration.upload(record);
	}

	@When("finding based on criteria {string} specified as {string}")
	public void finding_based_on_criteria_specified_as(String criteria, String entry) {
		System.out.println(Arrays.asList(record.getRecord()));
		System.out.println(Arrays.asList(finder.getJourney(criteria, entry, record).getRecord()));
	}

	@Then("show containers")
	public void show_containers() {
	    
	}

}
