import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.ClientDatabase;
import management.Container;
import management.ContainerDatabase;
import management.Journey;
import management.JourneyDatabase;

public class StepDefinitionDatabase {
	
	Client client;
	Container container;
	Journey journey;
	
	Boolean status;
	
	ClientDatabase clientDatabase = new ClientDatabase();
	ContainerDatabase containerDatabase = new ContainerDatabase();
	JourneyDatabase journeyDatabase = new JourneyDatabase();
	
	@When("producing client, container and journey databases")
	public void producing_client_journey_and_container_databases() throws Exception {
		clientDatabase.produce();
		containerDatabase.produce();
		journeyDatabase.produce();
	}
	
	@Then("confirm producing")
	public void confirm_producing() throws Exception {
		clientDatabase.pull();
		containerDatabase.pull();
		journeyDatabase.pull();
		
		assertTrue(clientDatabase.getClients().isEmpty());
		assertTrue(containerDatabase.getContainers().isEmpty());
		assertTrue(journeyDatabase.getJourneys().isEmpty());
	}
	
	@Given("client, container and journey databases")
	public void client_journey_and_container_databases() throws Exception {
		clientDatabase.produce();
		clientDatabase.pull();
		containerDatabase.produce();
		containerDatabase.pull();
		journeyDatabase.produce();
		journeyDatabase.pull();
	}

	@Given("unsaved data")
	public void unsaved_data() {
		client = new Client();
		client.setId(100);
		clientDatabase.getClients().add(client);
		
		container = new Container();
		containerDatabase.getContainers().add(container);
		
		journey = new Journey();
		journey.setJourneyID("XX00000");
		journey.setOrigin("undefined");
		journey.setDestination("undefined");
		journey.setDepartureDate("undefined");
		journey.setArrivalDate("undefined");
		journeyDatabase.create(journey);
		
		containerDatabase.getContainers().get(0).setContainerID(100);
		containerDatabase.getContainers().get(0).setPosition("undefined");
		containerDatabase.getContainers().get(0).setAvailability(true);
		
		containerDatabase.book(client.getId(), "undefined", "undefined", "undefined", 1);
		journeyDatabase.registerTo(journey.getJourneyID(), "undefined", "undefined", containerDatabase.extract(client.getId()));
	}

	@When("saving")
	public void saving() throws Exception {
		clientDatabase.push();
		containerDatabase.push();
		journeyDatabase.push();
	}

	@Then("confirm database updates")
	public void confirm_database_updates() throws Exception {
		clientDatabase.pull();
		containerDatabase.pull();
		journeyDatabase.pull();
		
		assertEquals(clientDatabase.getClients().get(0).getId(), 100);
		assertEquals(containerDatabase.getContainers().get(0).getContainerID(), 100);
		
		for (Journey journey : journeyDatabase.getJourneys().keySet()) {
			assertEquals(journey.getJourneyID(), "XX00000");
		}
	}

	@When("pulling")
	public void pulling() {
		try {
			Files.delete(Paths.get("client_database.json"));
			Files.delete(Paths.get("container_database.json"));
			Files.delete(Paths.get("journey_database.json"));
			clientDatabase.pull();
			containerDatabase.pull();
			journeyDatabase.pull();
		} catch (Exception e) {
			e.printStackTrace();
			status = true;
		}
	}

	@Then("fail")
	public void fail() throws Exception {
	    assertTrue(status);
	}
	
}
