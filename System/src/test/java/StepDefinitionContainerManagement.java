import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.LogisticsCompany;
import response.ResponseObject;

public class StepDefinitionContainerManagement {
	
	Client client;
	Container container;
	Journey journey;
	ResponseObject response;
	
	ContainerDatabase containers = new ContainerDatabase();
	ContainerDatabase myContainers = new ContainerDatabase();

	String location;
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
		this.location = position;
		for (int i = 1; i <= number; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(position);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
	}
	
	@When("booking")
	public void booking() {
		client = new Client();
		client.setId(1);
		response = containers.book(client.getId(), location, contentType, company, quantity);
	}
	
	@Then("confirm booking {string} {int}")
	public void confirm_booking(String message, int code) throws Exception {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
		for (Container container : containers.extract(client.getId())) {
			assertEquals(container.getOwner(), this.client.getId());
			assertEquals(container.getPosition(), this.location);
			assertEquals(container.getContentType(), this.contentType);
			assertEquals(container.getCompany(), this.company);
		}
	}
	
	@Then("deny booking {string} {int}")
	public void deny_booking(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
	@When("registering container")
	public void registering_container() {
	    response = containers.register(location);
	}

	@Then("confirm registration {string} {int}")
	public void confirm_registration(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
		assertTrue(!containers.getContainers().isEmpty());
	}
	
	@Then("deny registration {string} {int}")
	public void deny_registration(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
		assertTrue(containers.getContainers().isEmpty());
	}
	
	@Given("{int} containers registered by client {int} to journey {string} positioned in {string}")
	public void containers_registered_by_client_to_journey_positioned_in(Integer quantity, Integer clientID, String journeyID, String position) {
		
		int size = containers.getContainers().size();
		
		for (int i = 1 + size; i <= quantity + size; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(position);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		
		containers.book(clientID, position, "undefined", "undefined", quantity);
		
		for (Container container : containers.extract(clientID)) {
			container.setCurrentJourney(journeyID);
		}
	
	}

	@When("updating containers current position with current journey {string} to {string}")
	public void updating_containers_current_position_with_current_journey_to(String journeyID, String position) {
		response = containers.updatePosition(journeyID, position);
	}
	
	@Then("confirm position updating {string} {int}")
	public void confirm_position_updating(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);	
	}
	
	@Then("deny position updating {string} {int}")
	public void deny_position_updating(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
	@Given("{int} containers containing {string} registered by client {int} regulated by {string} to journey {string} positioned in {string}")
	public void containers_containing_registered_by_client_regulated_by_to_journey_positioned_in(Integer quantity, String contentType, Integer clientID, String company, String journeyID, String position) {
		
		int size = containers.getContainers().size();
		
		for (int i = 1 + size; i <= quantity + size; i++) {
			containers.getContainers().add(new Container());
			containers.getContainers().get(i - 1).setContainerID(i);
			containers.getContainers().get(i - 1).setPosition(position);
			containers.getContainers().get(i - 1).setAvailability(true);
		}
		
		containers.book(clientID, position, contentType, company, quantity);
		
		for (Container container : containers.extract(clientID)) {
			container.setCurrentJourney(journeyID);
		}
		
	}

	@When("finding containers based on criteria {string} specified as {string}")
	public void finding_containers_based_on_criteria_specified_as(String criteria, String entry) {
	    myContainers.setContainers(containers.find(criteria, entry));
	}
	
	@When("finding containers based on criteria {string} specified as {int}")
	public void finding_containers_based_on_criteria_specified_as(String criteria, int entry) {
	    myContainers.setContainers(containers.find(criteria, entry));
	}
	
	@When("finding containers based on criteria {string} specified as false")
	public void finding_containers_based_on_criteria_specified_as_false(String criteria) {
	    myContainers.setContainers(containers.find(false));
	}

	@Then("show containers with container-ID {int}")
	public void show_containers_with_container_ID(int containerID) {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.getContainerID(), containerID);
		}  
	}
	
	@Then("show containers with client-ID {int}")
	public void show_containers_with_client_ID(int clientID) {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.getOwner(), clientID);
		}  
	}
	
	@Then("show containers with position {string}")
	public void show_containers_with_position(String position) {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.getPosition(), position);
		}  
	}
	
	@Then("show containers with content-type {string}")
	public void show_containers_with_content_type(String contentType) {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.getContentType(), contentType);
		}  
	}
	
	@Then("show containers with company {string}")
	public void show_containers_with_company(String company) {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.getCompany(), company);
		}  
	}
	
	@Then("show containers with journey-ID {string}")
	public void show_containers_with_journey_ID(String journeyID) {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.getCurrentJourney(), journeyID);
		}  
	}
	
	@Then("show all occupied containers")
	public void show_all_occupied_containers() {
		for (Container container : myContainers.getContainers()) {
			assertEquals(container.isAvailability(), false);
		}
	}
		
	@When("updating containers current journey label with current journey {string}")
	public void updating_containers_current_journey_label_with_current_journey(String journeyID) {
		response = containers.markArrived(journeyID);
	}

	@Then("confirm journey label updating {string} {int}")
	public void confirm_journey_label_updating(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);	
	}
	
	@Then("deny journey label updating {string} {int}")
	public void deny_journey_label_updating(String message, int code) {
		assertEquals(response.getErrorMessage(), message);
		assertEquals(response.getErrorCode(), code);
	}
	
	@Given("a container with ID {int}")
	public void a_container_with_ID(int containerID) {
	    container = new Container();
	    container.setContainerID(containerID);
	    containers.getContainers().add(container);
	}

	@When("removing container number {int}")
	public void removing_container_number(int row) {
		containers.remove(row);
	}

	@Then("confirm removing of a container with ID {int}")
	public void confirm_removing_of_a_container_with_ID(Integer containerID) {
		for (Container container : containers.getContainers()) {
			assertTrue(container.getContainerID() != containerID);
		}
	}
	
	@Then("pass")
	public void pass() {
	    
	}
	
}
