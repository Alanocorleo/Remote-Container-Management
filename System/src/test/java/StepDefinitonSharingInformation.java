import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.LogisticsCompany;
import response.ResponseObject;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class StepDefinitonSharingInformation {
	
	LogisticsCompany comp = LogisticsCompany.getInstance();
	Client friend1 = new Client("Alice", "Allison","01\01\2000" ,"AliceAllison@gmail.com", "12345678", "password");
	Client friend2 = new Client("bob", "bobinison", "11\02\2002","Bob@gmail.com" , "12345679");
	ResponseObject response1;
	ResponseObject response2;
	Client seenFriend ;
	
	//Scenario 1: Send your information to another client successfully 
	@Given("two registed clients2")
	public void two_registed_clients2() throws Exception {
		comp.cleanDataBase();
		comp.CreateNewClient(friend1);
		comp.CreateNewClient(friend2);

	}
	
	@When("one client sends his information to the other client")
	public void one_client_sends_his_information_to_the_other_client() {
	    response1 = friend1.addFriend("Bob@gmail.com", comp.getClientDatabase());
	}
	
	@Then("The information is successfully sent")
	public void the_information_is_successfully_sent() {
	    assertTrue("information shared successfully", response1.getErrorCode()==11560);
	}
	//Scenario 2: Send your information to another client unsuccessfully 
	@Given("one registed client and his unregisted friend")
	public void one_registed_client_and_his_unregisted_friend() throws Exception {
		comp.CreateNewClient(friend1);
	}
	
	@When("one client sends his information to the unregisted client")
	public void one_client_sends_his_information_to_the_unregisted_client() {
	    response2 = friend1.addFriend("Bobthatdoesnotexist@gmail.com", comp.getClientDatabase());


	}
	
	@Then("The information is not successfully sent")
	public void the_information_is_not_successfully_sent() {
	    assertTrue("information shared successfully", response2.getErrorCode()==11021);
	}
	
	
	//Scenario being able to access friend's shared information
	@When("one client tries to access his friend's information")
	public void one_client_tries_to_access_his_friend_s_information() {
		friend2.updateFriendsList(1);
	}
	
	@Then("The information received successfully.")
	public void the_information_received_successfully() {
		seenFriend = friend2.getMyFriends(comp.getClientDatabase()).get(0);
	    assertTrue("information shared successfully", seenFriend.getId() == friend1.getId());
	    assertTrue("information shared successfully", seenFriend.getfirstName().equals(friend1.getfirstName()));
	    assertTrue("information shared successfully", seenFriend.getlastName().equals(friend1.getlastName()));
	    assertTrue("information shared successfully", seenFriend.getEmail().equals(friend1.getEmail()));
	    assertTrue("information shared successfully", seenFriend.getPhoneNumber().equals(friend1.getPhoneNumber()));


	}

}