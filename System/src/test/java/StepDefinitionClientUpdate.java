import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import management.Client;
import management.LogisticsCompany;
import response.ResponseObject;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class StepDefinitionClientUpdate {
	
	LogisticsCompany logisticsCompany = LogisticsCompany.getInstance();
	Client newClient = new Client();
	Client registeredClient = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	Client clientWithSameNameasRegisteredClient = new Client("Alice", "Allison", "999999","AliceAllison12@gmail.com" , "12345679");
	Client clientWithoutEmail;
	Client clientWithoutPhoneNumber;
	Client registeredClient2 = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
	LogisticsCompany logisticscompany2;
	Client unregisteredClient;
	ArrayList <Client> theReturnedClientList;
	ResponseObject response1;
	ResponseObject response2;
	
	
	//client update feature
		//scenario 1: Successful update of all information except name and birth date
		
		@Given("registered client")
		public void registered_client() throws Exception {
			logisticsCompany.cleanDataBase();

			logisticsCompany.CreateNewClient(registeredClient2);

		}

		@When("the client wants to update all their information except their name and birth date")
		public void the_client_wants_to_update_all_their_information_except_their_name_and_birth_date() throws Exception {
		    response1 = registeredClient2.updateInfo("Alice", "Allison", "01012000", "AliceAllison12@gmail.com", "12345679", logisticsCompany.getClientDatabase());
		}

		@Then("the informtion is updated successfully")
		public void the_informtion_is_updated_successfully() {
			assertTrue(response1.getErrorCode()==1004);
		    
		}
		//scenario 2: UnSuccessful update due to the client not being registered by the company
		
		@Given("unregistered client")
		public void unregistered_client() {
			logisticsCompany.cleanDataBase();

			unregisteredClient = new Client("Alice", "Allison","01012000" ,"AliceAllison@gmail.com", "12345678");
		    
		}

		@When("the person wants to update all their information except their name and birth date")
		public void the_person_wants_to_update_all_their_information_except_their_name_and_birth_date() throws Exception {
			unregisteredClient.updateInfo("Alice","Allison","01012000" ,"AliceAllison12@gmail.com", "12345679", logisticsCompany.getClientDatabase());
		   	}

		@Then("the informtion is not updated successfully")
		public void the_informtion_is_not_updated_successfully() {
		   assertFalse(logisticsCompany.getClientDatabase().checkUpdatedInfo("Alice", "Allison", "01012000", "AliceAllison12@gmail.com", "12345679"));
		}
		
		//scenario 3: UnSuccessful update due to illegal phone number
		
		@Given("a phone number that exists in the system")
		public void a_phone_number_that_exists_in_the_system() throws Exception {
			logisticsCompany.cleanDataBase();

			logisticsCompany.CreateNewClient(new Client("Alice", "Bobbinson","01012000","AliceAllison12@gmail.com", "12345679"));
			logisticsCompany.CreateNewClient(registeredClient2);

		    
		}

		@When("the client wants to update all their contact phone number to already existing phone number")
		public void the_client_wants_to_update_all_their_contact_phone_number_to_already_existing_phone_number() throws Exception {
			response1 = registeredClient2.updateInfo("Alice", "Allison","01012000", "AliceAllison@gmail.com", "12345679",  logisticsCompany.getClientDatabase());
		}

		@Then("the informtion is not updated")
		public void the_informtion_is_not_updated() {
			assertTrue(response1.getErrorCode()== 1006);
		    
		}
		
		//scenario 4: UnSuccessful update due to illegal email
		
		@Given("a email that exists in the system")
		public void a_email_that_exists_in_the_system() throws Exception {
			logisticsCompany.cleanDataBase();

			logisticsCompany.CreateNewClient(new Client("Alice", "Bobbinson","01012000" ,"AliceAllison12@gmail.com", "12345679"));
			logisticsCompany.CreateNewClient(registeredClient2);

		}

		@When("the client wants to update all their contact phone number to already existing email")
		public void the_client_wants_to_update_all_their_contact_phone_number_to_already_existing_email() throws Exception {
			response1 = registeredClient2.updateInfo("Alice", "Allison","01012000" ,"AliceAllison12@gmail.com", "12345678",  logisticsCompany.getClientDatabase());
		}

		@Then("the informtion is not updated version {int}")
		public void the_informtion_is_not_updated_version(Integer int1) {
			assertTrue(response1.getErrorCode()==1005);
		}

}