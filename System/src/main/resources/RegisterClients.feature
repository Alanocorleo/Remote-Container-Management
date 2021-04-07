#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Register clients
		I want to use this template for my feature file

  @tag1
  Scenario: A client is registering in the system with date of birth, full name and email, phone number
    Given a new client with name "Alice Allison" 
    And born 01012000  
    And with email "AliceAllison@gmail.com" 
    And with phone number 12345678
    When the company registers the client
    Then the client is registered in the system successfully
    And the client is assigned a unique company ID 1
    
    
	Scenario: Same client registers again
		Given a registered client 
		When the registered client tries to register again 
	  Then the client is not registered in the system again
	  
  
 Scenario: Two client with same name but different contact info register
	  Given an already registered client
	  When a new client registers with the same name but different contact info  
		Then the new client is registered in the system successfully
		And both clients have different unique company IDs
		
	
 Scenario: Client tries to register without name
	  Given a client without a name
    And born 01012000  
    And with email "AliceAllison@gmail.com" 
    And with phone number 12345678
	  When they try to regsiter without a name
		Then then the client is not registered in the system
		
		
		
 Scenario: Client tries to register with one contact info
	  Given a client with all info except email
	  When they try to regsiter without an email
		Then then the client is registered in the system successfully
		
		
	Scenario: Client tries to register with the other contact info
	  Given a client with all info except phone number
	  When they try to regsiter without an phone number
		Then then the client without the phone numver is registered in the system successfully
		
		
		
		
  


