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
Feature: Update information
	Client wants to update their personal information.
	
  @tag1
  Scenario: Successfull update of al information except name and birth date
    Given registered client
    When the client wants to update all their information except their name and birth date
    Then the informtion is updated successfully
 
	Scenario: Unsuccessful update due to the client not being registered by the company
	  Given unregistered client
	  When the person wants to update all their information except their name and birth date
	  Then the informtion is not updated successfully
  
 Scenario: Unsuccessful update due to illegal phone number
  Given registered client
  And a phone number that exists in the system
  When the client wants to update all their contact phone number to already existing phone number
  Then the informtion is not updated 
  
  
 Scenario: Unsuccessful update due to illegal email
  Given registered client
  And an email that exists in the system
  When the client wants to update all their contact phone number to already existing email
  Then the informtion is not updated version 2