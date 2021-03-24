import clientsManagement.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import journeysManagement.Container;
import journeysManagement.Registration;
import journeysManagement.ResponseObject;
import journeysManagement.Record;

public class StepDefinitionM2 {
	
	Client client = new Client();
	Container container = new Container();
	Registration registration;
	Record record = new Record();
	ResponseObject response;
	String message;
	
	@Given("client-ID {int}")
	public void a_client_ID(int id) {
		container.setOwner(id);
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

	@Then("create a journey-ID {string}")
	public void create_a_journey_ID(String journeyID) {
		registration.createJourney();
	}

	@Then("put on record")
	public void put_on_record() { 
		record.put(container);
	}
	
	@Then("system displays message that some information is missing")
	public void system_displays_message_that_some_information_is_missing() {
		String message = response.getResponse();
		System.out.print(message);
	}

}
