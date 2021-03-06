import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.Container;
//import management.LogisticCompany;
import management.LogisticsCompany;

public class StepDefinitionHistoryBook {

	Client client;
	LogisticsCompany company;
	Container container;
	
	@Given("^a container with id (\\d+)$")
	public void a_container_with_id(int id)  {
	    container = new Container();
	    container.setContainerID(id);
	}

	@Given("^a logisitcs company \"([^\"]*)\"$")
	public void a_logisitcs_company(String name)  {
	    company = LogisticsCompany.getInstance();
	    company.setName(name);
	}

	@When("^logisitcs company chooses the container with journey-ID (\\d+)$")
	public void logisitcs_company_chooses_the_container_with_journey_ID(int id)  {
    	for (int i = 0; i < company.getContainerDatabase().getContainers().size(); i++) {
    		if (company.getContainerDatabase().getContainers().get(i).getContainerID() == id) {
    			container = company.getContainerDatabase().getContainers().get(i);
    		}
    	}
	}

	@When("^add Temperature (\\d+)$")
	public void add_Temperature(int temp)  {
		container.setTemperature(temp);
	}

	@When("^add Humidity (\\d+)$")
	public void add_Humidity(int humid)  {
    	container.setHumidity(humid);
	}

	@When("^add Pressure (\\d+)$")
	public void add_Pressure(int pres)  {
    	container.setPressure(pres);
	}

	@When("^add Position \"([^\\\"]*)\"$")
	public void add_Position(String pos)  {
    	container.setPosition(pos);
	}

	@Then("^update container's values$")
	public void update_container_s_values()  {
	    container.appendHistory();
	}
	
	@When("^requesting to see info for container (\\d+)$")
	public void requesting_to_see_info_for_container(int id)  {
		if (container.getOwner() == client.getId()) {
			container.getHistory();
		} else {
			System.out.println("This container does not belong to you");
		}
	}

	@Then("^history of container is shown$")
	public void history_of_container_is_shown()  {
		container.setDate("12\\12\\1212");
		container.setHumidity(1);
		container.setTemperature(1);
		container.setPressure(1);
		container.setPosition("Oslo");
		if (container.getOwner() == client.getId()) {
			container.getHistory().show();
			container.getHistory().showTable();
		} else {
			System.out.println("This container does not belong to you");
		}
	}
	
	@Then("^show message: container does not exist$")
	public void show_message_container_does_not_exist()  {
	    System.out.println("This container does not exist");
	}
	
	@Given("^a container with id (\\d+) and owner (\\d+)$")
	public void a_container_with_id_and_owner(int id, int owner)  {
	    container = new Container();
	    container.setContainerID(id);
	    container.setOwner(owner);
	    container.appendHistory();
	}
	
	@Given("^a client with id (\\d+)$")
	public void a_client_with_id(int id)  {
	    client = new Client();
	    client.setId(id);
	}
	
	@Then("^history of container is not shown$")
	public void history_of_container_is_not_shown()  {
		if (container.getOwner() == client.getId()) {
			container.getHistory().show();
		} else {
			System.out.println("This container does not belong to you");
		}
	}
	
}