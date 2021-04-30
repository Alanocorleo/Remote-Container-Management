import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.JourneyDatabase;
import management.LogisticsCompany;
import response.ResponseObject;

public class StepDefinitionJourneyManagement {

	Client client;
	Journey journey;
	ResponseObject response;

	JourneyDatabase journeys = new JourneyDatabase();
	JourneyDatabase myJourneys = new JourneyDatabase();
	ContainerDatabase containers = new ContainerDatabase();
	ContainerDatabase myContainers = new ContainerDatabase();

	String journeyID;
	String origin;
	String destination;
	String departureDate;
	String arrivalDate;
	String location;
	String contentType;
	String company;
	int quantity;

	@Given("journey {string} from {string} to {string}")
	public void journey_from_to(String journeyID, String origin, String destination) {
		journey = new Journey();
		journey.setJourneyID(journeyID);
		journey.setOrigin(origin);
		journey.setDestination(destination);
		journey.setDepartureDate("undefined");
		journey.setArrivalDate("undefined");
		response = journeys.create(journey);
		assertEquals(response.getErrorMessage(), "Journey has been created");
		assertEquals(response.getErrorCode(), 020);
	}
	
	@Given("{int} booked containers from {string}")
	public void booked_containers_from(Integer number, String position) {
		this.location = position;
		for (int i = 1; i <= number; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(position);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		client = new Client();
		containers.book(client.getId(), location, contentType, company, quantity);
	}
	
	@Given("origin {string}")
	public void origin(String origin) {
		this.origin = origin;
	}

	@Given("destination {string}")
	public void destination(String destination) {
		this.destination = destination;
	}
	
	@Given("departure date {string}")
	public void departure_date(String departureDate) {
		this.departureDate = departureDate;
	}
	
	@Given("arrival date {string}")
	public void arrival_date(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@When("registering to {string}")
	public void registering_to(String journeyID) {
	    response = journeys.registerTo(journeyID, origin, destination, containers.extract(client.getId()));
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
	
	@Given("{int} containers registered by client {int} to journey {string} from {string} to {string} departing {string} and arriving {string}")
	public void containers_registered_to_journey_by_client_from_to(Integer quantity, Integer clientID, String journeyID, String origin, String destination, String departureDate, String arrivalDate) {
		journey = new Journey();
		journey.setJourneyID(journeyID);
		journey.setOrigin(origin);
		journey.setDestination(destination);
		journey.setDepartureDate(departureDate);
		journey.setArrivalDate(arrivalDate);
		journeys.create(journey);
		
		int size = containers.getContainers().size();	
		
		for (int i = 1 + size; i <= quantity + size; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(origin);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		
		containers.book(clientID, origin, "undefined", "undefined", quantity);
		journeys.registerTo(journeyID, origin, destination, containers.extract(clientID));
	}

	@When("updating containers current position of journey {string} to {string}")
	public void updating_containers_current_position_of_journey_to(String journeyID, String position) {
		response = journeys.updatePosition(journeyID, position);
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
		
	@When("finding based on criteria {string} specified as {string} for client {int}")
	public void finding_based_on_criteria_specified_as_for_client(String criteria, String entry, int clientID) throws Exception {
		myJourneys.setJourneys(journeys.extract(clientID));
		myJourneys.setJourneys(myJourneys.find(criteria, entry));
	}
	
	@Then("show journeys with journey-ID {string} of client {int}")
	public void show_journeys_with_journeyID_owned_by_client(String journeyID, int clientID) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getJourneyID(), journeyID);
			for (Container contianer : myJourneys.getJourneys().get(key)) {
				assertEquals(contianer.getOwner(), clientID);
			}
		}
	}
	
	@Then("show journeys with origin {string} of client {int}")
	public void show_journeys_with_origin_owned_by_client(String origin, int clientID) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getOrigin(), origin);
			for (Container contianer : myJourneys.getJourneys().get(key)) {
				assertEquals(contianer.getOwner(), clientID);
			}
		}
	}
	
	@Then("show journeys with destination {string} of client {int}")
	public void show_journeys_with_destination_of_client(String destination, int clientID) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getDestination(), destination);
			for (Container contianer : myJourneys.getJourneys().get(key)) {
				assertEquals(contianer.getOwner(), clientID);
			}
		}
	}
	
	@Then("show journeys with departure date {string} of client {int}")
	public void show_journeys_with_departure_date_of_client(String departureDate, int clientID) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getDepartureDate(), departureDate);
			for (Container contianer : myJourneys.getJourneys().get(key)) {
				assertEquals(contianer.getOwner(), clientID);
			}
		}
	}
	
	@Then("show journeys with arrival date {string} of client {int}")
	public void show_journeys_with_arrival_date(String arrivalDate, int clientID) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getArrivalDate(), arrivalDate);
			for (Container contianer : myJourneys.getJourneys().get(key)) {
				assertEquals(contianer.getOwner(), clientID);
			}
		}
	}
	
	@When("finding based on criteria {string} specified as {string}")
	public void finding_based_on_criteria_specified_as(String criteria, String entry) throws Exception {
		myJourneys.setJourneys(journeys.find(criteria, entry));
	}
	
	@Then("show journeys with journey-ID {string}")
	public void show_journeys_with_journeyID(String journeyID) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getJourneyID(), journeyID);
		}
	}
	
	@Then("show journeys with origin {string}")
	public void show_journeys_with_origin(String origin) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getOrigin(), origin);
		}
	}
	
	@Then("show journeys with destination {string}")
	public void show_journeys_with_destination(String destination) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getDestination(), destination);
		}
	}
	
	@Then("show journeys with departure date {string}")
	public void show_journeys_with_departure_date(String departureDate) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getDepartureDate(), departureDate);
		}
	}
	
	@Then("show journeys with arrival date {string}")
	public void show_journeys_with_arrival_date(String arrivalDate) {
		for (Journey key : myJourneys.getJourneys().keySet()) {
			assertEquals(key.getArrivalDate(), arrivalDate);
		}
	}
	
	@When("creating journey")
	public void creating_journey() {
		journey = new Journey();
		journey.setJourneyDatabase(journeys);
	    journey.setOrigin(origin);
	    journey.setDestination(destination);
	    journey.setDepartureDate(departureDate);
	    journey.setArrivalDate(arrivalDate);
	    response = journeys.create(journey);
	}
	
	@Then("confirm journey creation {string} {int}")
	public void confirm_journey_creation(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
	@Then("assign a journey-ID")
	public void assign_a_journeyID() {
		journey.createJourneyID();
		for (Journey journey : journeys.getJourneys().keySet()) {
			assertEquals(journey.getJourneyID().substring(0,2), String.format("%c%c", journey.getJourneyID().charAt(0), journey.getJourneyID().charAt(1)));
			assertTrue(journey.getJourneyID().matches("[A-Z]{1}\\w{2}\\d{4}"));
		}
	}

	@Then("deny journey creation {string} {int}")
	public void deny_journey_creation(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
//	@Then("show journeys with content-type {string} of client {int}")
//	public void show_journeys_with_content_type_of_client(String contentType, int clientID) {
//		for (Journey key : myJourneys.getJourneys().keySet()) {
//			for (Container contianer : myJourneys.getJourneys().get(key)) {
//				assertEquals(contianer.getOwner(), clientID);
//				assertEquals(contianer.getContentType(), contentType);
//			}
//		}
//	}
//		
//	@Then("show journeys with company {string} of client {int}")
//	public void show_journeys_with_company_of_client(String company, int clientID) {
//		for (Journey key : myJourneys.getJourneys().keySet()) {
//			for (Container contianer : myJourneys.getJourneys().get(key)) {
//				assertEquals(contianer.getOwner(), clientID);
//				assertEquals(contianer.getCompany(), company);
//			}
//		}
//	}
	

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

}
