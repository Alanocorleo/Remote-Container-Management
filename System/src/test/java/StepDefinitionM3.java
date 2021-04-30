import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.Container;
//import management.LogisticCompany;
import management.LogisticsCompany;

public class StepDefinitionM3 {

	Container container;
	LogisticsCompany logcom;
	Client client;
	
	@Given("^a container with id (\\d+)$")
	public void a_container_with_id(int id)  {
	    container = new Container();
	    container.setContainerID(id);
	}

	@Given("^a logisitc company \"([^\"]*)\"$")
	public void a_logisitc_company(String name)  {
	    logcom = LogisticsCompany.getInstance();
	    logcom.setName(name);
	}

	@When("^logisitc company chooses the container with journey id (\\d+)$")
	public void logisitc_company_chooses_the_container_with_journey_id(int id)  {
	    //logcom.Chosen(id);
		}

	@When("^add Temperature (\\d+)$")
	public void add_Temperature(int temp)  {
//		if (logcom.getChosenContainer() == container.getContainerID()) {
//			container.setTemperature(temp);
//		} else {
//			
//			System.out.print("Wrong container is chosen!");
//		}
	    	
	}

	@When("^add Humidity (\\d+)$")
	public void add_Humidity(int humid)  {
	    
//	    if (logcom.getChosenContainer() == container.getContainerID()) {
//	    	container.setHumidity(humid);
//		} else {
//			
//			System.out.print("Wrong container is chosen!");
//		}
	}

	@When("^add Pressure (\\d+)$")
	public void add_Pressure(int pres)  {
	    
//	    if (logcom.getChosenContainer() == container.getContainerID()) {
//	    	container.setPressure(pres);
//		} else {
//			
//			System.out.print("Wrong container is chosen!");
//		}
	}

	@When("^add Position \"([^\\\"]*)\"$")
	public void add_Position(String pos)  {
	    
//	    if (logcom.getChosenContainer() == container.getContainerID()) {
//	    	container.setPosition(pos);
//		} else {
//			
//			System.out.print("Wrong container is chosen!");
//		}
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
			
			System.out.println("this container does not belong to you");
		}
	}

	@Then("^history of container is shown$")
	public void history_of_container_is_shown()  {
		
		if (container.getOwner() == client.getId()) {
			container.getHistory().show();
		} else {
			
			System.out.println("this container does not belong to you");
		}
		
	}
	
	@Then("^show message: container does not exist$")
	public void show_message_container_does_not_exist()  {
	    System.out.println("this container does not exist!");
	}
	
	@Given("^a container with id (\\d+) and owner (\\d+)$")
	public void a_container_with_id_and_owner(int id, int owner)  {
	    container = new Container();
	    container.setContainerID(id);
	    container.setOwner(owner);
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
			
			System.out.println("this container does not belong to you");
		}
		
	}
	
	
	
}