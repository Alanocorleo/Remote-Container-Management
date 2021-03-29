import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	Record myJourneys;
	
	@Given("client")
	public void client() {
		client = new Client();
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
		registration = new Registration(client, record);
		response = registration.register(container);
	}

	@Then("confirm registration")
	public void confirm_registration() {
		assertEquals(response.getErrorMessage(), "Container has been registered");
		assertEquals(response.getErrorCode(), 010);
	}
	
	@Then("create journey-ID {string}")
	public void create_journey_ID(String journeyID) {
		registration.createJourneyID();
		assertEquals(registration.getJourneyID().substring(0,2), String.format("%c%c", journeyID.charAt(0), journeyID.charAt(1)));
		assertTrue(registration.getJourneyID().matches("[A-Z]{1}\\w{2}\\d{4}"));
	}

	@Then("put on record")
	public void put_on_record() { 
		response = registration.upload();
		assertEquals(response.getErrorMessage(), "Journey has been uploaded");
		assertEquals(response.getErrorCode(), 012);
		assertEquals(record.getRecord().get(registration.getJourneyID()), container);
	}
	
	@Then("deny registration")
	public void deny_registration() {
		assertEquals(response.getErrorMessage(), "Some necessary parameters are not entered");
		assertEquals(response.getErrorCode(), 110);
	}

	@Given("company manager {string}")
	public void company_manager(String company) {
		manager = new Manager(company, record);
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
		registration = new Registration(client, record);
		registration.register(container);
		registration.setJourneyID("CO00001");
		registration.upload();
	}

	@When("updating containers current position of journey {string} to {string}")
	public void updating_containers_current_position_of_journey_to(String journeyID, String position) {
		response = manager.updatePosition(journeyID, position);
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
		response = manager.completeJourney(journeyID);
	    
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
		client.setId(00001);
		Container container1 = new Container(); 
		container1.setOwner(client.getId());
		container1.setOrigin("Copenhagen");
		container1.setDestination("Oslo");
		container1.setContentType("Fish");
		container1.setCompany("Maersk");
		registration = new Registration(client, record);
		registration.register(container1);
		registration.setJourneyID("CO00001");
		registration.upload();
		
		Container container2 = new Container(); 
		container2.setOwner(client.getId());
		container2.setOrigin("Amsterdam");
		container2.setDestination("Copenhagen");
		container2.setContentType("Flowers");
		container2.setCompany("Maersk");
		registration.register(container2);
		registration.setJourneyID("AC00001");
		registration.upload();
		
		Container container3 = new Container(); 
		container3.setOwner(client.getId());
		container3.setOrigin("Copenhagen");
		container3.setDestination("Gothenburg");
		container3.setContentType("Fish");
		container3.setCompany("DSV");
		registration.register(container3);
		registration.setJourneyID("CG00001");
		registration.upload();
		
		Client client2 = new Client();
		client2.setId(00002);
		Container container4 = new Container(); 
		container4.setOwner(client2.getId());
		container4.setOrigin("Copenhagen");
		container4.setDestination("Oslo");
		container4.setContentType("Fish");
		container4.setCompany("Maersk");
		registration = new Registration(client2, record);
		registration.register(container4);
		registration.setJourneyID("CO00002");
		registration.upload();
	}

	@When("finding based on criteria {string} specified as {string}")
	public void finding_based_on_criteria_specified_as(String criteria, String entry) {
		 finder = new Finder(client, record);
		 myJourneys = finder.getJourneys(criteria, entry);	 
	}

	@Then("show journeys with origin {string}")
	public void show_journeys_with_origin(String origin) {
		assertEquals(myJourneys.getRecord().get("CO00001").getOrigin(), origin);
		assertNull(myJourneys.getRecord().get("AC00001"));
		assertEquals(myJourneys.getRecord().get("CG00001").getOrigin(), origin);
		assertNull(myJourneys.getRecord().get("CO00002"));
		
	}
	
	@Then("show journeys with destination {string}")
	public void show_journeys_with_destination(String destination) {
		assertEquals(myJourneys.getRecord().get("CO00001").getDestination(), destination);
		assertNull(myJourneys.getRecord().get("AC00001"));
		assertNull(myJourneys.getRecord().get("CG00001"));
		assertNull(myJourneys.getRecord().get("CO00002"));
	}

	@Then("show journeys with content-type {string}")
	public void show_journeys_with_content_type(String contentType) {
		assertNull(myJourneys.getRecord().get("CO00001"));
		assertEquals(myJourneys.getRecord().get("AC00001").getContentType(), contentType);
		assertNull(myJourneys.getRecord().get("CG00001"));
		assertNull(myJourneys.getRecord().get("CO00002"));
	}
	
	@Then("show journeys with company {string}")
	public void show_journeys_with_company(String company) {
		assertEquals(myJourneys.getRecord().get("CO00001").getCompany(), company);
		assertEquals(myJourneys.getRecord().get("AC00001").getCompany(), company);
		assertNull(myJourneys.getRecord().get("CG00001"));
		assertNull(myJourneys.getRecord().get("CO00002"));
	}
	
	@Then("show all client journeys")
	public void show_all_client_journeys() {
		assertNotNull(myJourneys.getRecord().get("CO00001"));
		assertNotNull(myJourneys.getRecord().get("AC00001"));
		assertNotNull(myJourneys.getRecord().get("CG00001"));
		assertNull(myJourneys.getRecord().get("CO00002"));
	}

}
