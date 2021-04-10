import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import clientsManagement.Client;
import journeysManagement.Company;
import journeysManagement.Container;
import journeysManagement.ContainerDatabase;
import journeysManagement.Journey;
import journeysManagement.JourneyDatabase;
import journeysManagement.ResponseObject;

public class StepDefinitionM2 {

	Client client;
	Company logisticCompany;
	Journey journey;
	Map<Container, Journey> myContainers;
	ResponseObject response;

	JourneyDatabase journeys = new JourneyDatabase();
	ContainerDatabase containers = new ContainerDatabase();
	
	String location;
	String origin;
	String destination;
	String contentType;
	String company;
	int quantity;
	
	@Given("location {string}")
	public void location(String location) {
		this.location = location;
	}

	@Given("content-type {string}")
	public void content_type(String contentType) {
		this.contentType = contentType;
	}

	@Given("company {string}")
	public void company(String company) {
		this.company = company;
	}
	
	@Given("number of containers {int}")
	public void number_of_containers(int number) {
		this.quantity = number;
	}


	@Given("shipping yard in {string} with {int} containers")
	public void shipping_yard_with_containers(String position, Integer number) {
		for (int i = 1; i <= number; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(position);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
	}
	
	@When("registering")
	public void registering() {
		client = new Client();
		response = client.registerContainers(location, contentType, company, quantity, containers);
		
		for (Container container : client.getMyContainers()) {
			assertEquals(container.getOwner(), this.client.getId());
			assertEquals(container.getPosition(), this.location);
			assertEquals(container.getContentType(), this.contentType);
			assertEquals(container.getCompany(), this.company);
		}
	}
	
	@Given("journey {string} from {string} to {string}")
	public void journey_from_to(String journeyID, String origin, String destination) {
		journey = new Journey(journeys);
		journey.setJourneyID(journeyID);
		journey.setOrigin(origin);
		journey.setDestination(destination);	
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Journey has been created");
		assertEquals(response.getErrorCode(), 012);
		
	}
	
	@Given("origin {string}")
	public void origin(String origin) {
		this.origin = origin;
	}

	@Given("destination {string}")
	public void destination(String destination) {
		this.destination = destination;
	}

	@Given("{int} registered containers from {string}")
	public void registered_containers_from(Integer number, String position) {
		for (int i = 1; i <= number; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(position);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		client = new Client();
		client.registerContainers(position, "undefined", "undefined", number, containers);
	}

	@When("registering to {string}")
	public void registering_to(String journeyID) {
	    response = journeys.registerTo(journeyID, origin, destination, client.getMyContainers());
	}
	
	@Then("confirm registering {string} {int}")
	public void confirm_registering(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
	@Then("deny registering {string} {int}")
	public void deny_registering(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}

	
//	@Then("create journey-ID {string}")
//	public void create_journey_ID(String journeyID) {
//		journey.createJourneyID();
//		assertEquals(journey.getJourneyID().substring(0,2), String.format("%c%c", journeyID.charAt(0), journeyID.charAt(1)));
//		assertTrue(journey.getJourneyID().matches("[A-Z]{1}\\w{2}\\d{4}"));
//	}
//
//	@Then("put on record")
//	public void put_on_record() { 
//		response = journeys.upload(journey.getJourneyID(), container);
//		assertEquals(response.getErrorMessage(), "Journey has been uploaded");
//		assertEquals(response.getErrorCode(), 012);
//		assertEquals(journeys.getRecord().get(journey.getJourneyID()), container);
//	}
	
	@Given("{int} containers registered to journey {string} from {string} to {string} regulated by {string}")
	public void containers_registered_to_journey_from_to_regulated_by(Integer quantity, String journeyID, String origin, String destination, String company) {
		journey = new Journey(journeys);
		journey.setJourneyID(journeyID);
		journey.setOrigin(origin);
		journey.setDestination(destination);	
		journeys.create(journey);
		
		for (int i = 1; i <= quantity; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(origin);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		client = new Client();
		client.registerContainers(origin, "undefined", company, quantity, containers);
		journeys.registerTo(journeyID, origin, destination, client.getMyContainers());
	}

	@Given("logistic company {string}")
	public void logistic_company(String company) {
		logisticCompany = new Company();
		logisticCompany.setName(company);	
	}

	@When("updating containers current position of journey {string} to {string}")
	public void updating_containers_current_position_of_journey_to(String journeyID, String position) {
		response = logisticCompany.updatePosition(journeyID, position, journeys);
		this.location = position;
	}

	@Then("confirm updating {string} {int}")
	public void confirm_updating(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);	
	}
	
	@Then("deny updating {string} {int}")
	public void deny_updating(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
	@Given("{int} containers registered to journey {string} by client {int} from {string} to {string} regulated by {string}")
	public void containers_registered_to_journey_by_client_from_to_regulated_by(int quantity, String journeyID, int clientID, String origin, String destination, String company) {
		journey = new Journey(journeys);
		journey.setJourneyID(journeyID);
		journey.setOrigin(origin);
		journey.setDestination(destination);	
		journeys.create(journey);
		
		
		for (int i = 1; i <= 20; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(origin);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		
		client = new Client();
		client.setId(clientID);
		
//		for (Container container: containers.getContainers()) {
//			System.out.println(container.getPosition());
//		}
		
		client.registerContainers(origin, "undefined", company, quantity, containers);
		
		for (Container container: containers.getContainers()) {
			System.out.println(container.isAvailability());
		}
		
		journeys.registerTo(journeyID, origin, destination, client.getMyContainers());
	}
	
	@When("finding based on criteria {string} specified as {string} for client {int}")
	public void finding_based_on_criteria_specified_as_for_client(String criteria, String entry, int clientID) {
		 myContainers = journeys.find(criteria, entry, clientID); 
	}
	
	@Then("show journeys with origin {string} owned by client {int}")
	public void show_journeys_with_origin_owned_by_client(String origin, int clientID) {
		System.out.println(myContainers.toString());
	}

//	
//	@When("completing journey {string}")
//	public void completing_journey(String journeyID) {
//		response = manager.completeJourney(journeyID);
//	    
//	}
//
//	@Then("remove from record")
//	public void remove_from_record() {
//		assertEquals(record.getRecord().get("CO00001"), null);
//		assertEquals(response.getErrorMessage(), "Journey has been completed and succesfully removed from the record");
//		assertEquals(response.getErrorCode(), 050);
//	}
//
//	@Then("deny removal")
//	public void deny_removal() {
//		assertEquals(response.getErrorMessage(), "Journey was not found");
//		assertEquals(response.getErrorCode(), 700);
//	}
//	
//	@Given("recorded journeys")
//	public void recorded_journeys() {
//		client.setId(00001);
//		Container container1 = new Container(); 
//		container1.setOwner(client.getId());
//		container1.setOrigin("Copenhagen");
//		container1.setDestination("Oslo");
//		container1.setContentType("Fish");
//		container1.setCompany("Maersk");
//		registration = new Journey(client, record);
//		registration.register(container1);
//		registration.setJourneyID("CO00001");
//		registration.upload();
//		
//		Container container2 = new Container(); 
//		container2.setOwner(client.getId());
//		container2.setOrigin("Amsterdam");
//		container2.setDestination("Copenhagen");
//		container2.setContentType("Flowers");
//		container2.setCompany("Maersk");
//		registration.register(container2);
//		registration.setJourneyID("AC00001");
//		registration.upload();
//		
//		Container container3 = new Container(); 
//		container3.setOwner(client.getId());
//		container3.setOrigin("Copenhagen");
//		container3.setDestination("Gothenburg");
//		container3.setContentType("Fish");
//		container3.setCompany("DSV");
//		registration.register(container3);
//		registration.setJourneyID("CG00001");
//		registration.upload();
//		
//		Client client2 = new Client();
//		client2.setId(00002);
//		Container container4 = new Container(); 
//		container4.setOwner(client2.getId());
//		container4.setOrigin("Copenhagen");
//		container4.setDestination("Oslo");
//		container4.setContentType("Fish");
//		container4.setCompany("Maersk");
//		registration = new Journey(client2, record);
//		registration.register(container4);
//		registration.setJourneyID("CO00002");
//		registration.upload();
//	}
//

	@Then("show journeys with origin {string}")
	public void show_journeys_with_origin(String origin) {
//		assertEquals(myJourneys.getRecord().get("CO00001").getOrigin(), origin);
//		assertNull(myJourneys.getRecord().get("AC00001"));
//		assertEquals(myJourneys.getRecord().get("CG00001").getOrigin(), origin);
//		assertNull(myJourneys.getRecord().get("CO00002"));
		
	}
//	
//	@Then("show journeys with destination {string}")
//	public void show_journeys_with_destination(String destination) {
//		assertEquals(myJourneys.getRecord().get("CO00001").getDestination(), destination);
//		assertNull(myJourneys.getRecord().get("AC00001"));
//		assertNull(myJourneys.getRecord().get("CG00001"));
//		assertNull(myJourneys.getRecord().get("CO00002"));
//	}
//
//	@Then("show journeys with content-type {string}")
//	public void show_journeys_with_content_type(String contentType) {
//		assertNull(myJourneys.getRecord().get("CO00001"));
//		assertEquals(myJourneys.getRecord().get("AC00001").getContentType(), contentType);
//		assertNull(myJourneys.getRecord().get("CG00001"));
//		assertNull(myJourneys.getRecord().get("CO00002"));
//	}
//	
//	@Then("show journeys with company {string}")
//	public void show_journeys_with_company(String company) {
//		assertEquals(myJourneys.getRecord().get("CO00001").getCompany(), company);
//		assertEquals(myJourneys.getRecord().get("AC00001").getCompany(), company);
//		assertNull(myJourneys.getRecord().get("CG00001"));
//		assertNull(myJourneys.getRecord().get("CO00002"));
//	}
//	
//	@Then("show all client journeys")
//	public void show_all_client_journeys() {
//		assertNotNull(myJourneys.getRecord().get("CO00001"));
//		assertNotNull(myJourneys.getRecord().get("AC00001"));
//		assertNotNull(myJourneys.getRecord().get("CG00001"));
//		assertNull(myJourneys.getRecord().get("CO00002"));
//	}

}
