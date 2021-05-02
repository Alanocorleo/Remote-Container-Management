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
Feature: Contacting clients
  Company wants to have access to clients personal information to contact them

  @tag1
  Scenario: Contacting registered client by unique id
    Given registered client2
    When the company searches for the client by id 
    Then the company gets all the info of the client
 
    
  Scenario: Contacting registered client by name
    Given atleast 2 registered clients with the same name
    When the company searches for the client by name
    Then the company gets all the info of the client of both clients
    
    
  Scenario: Contacting client by id that doesnt exists
    Given a registery 
    When the company searches for the client by the non existing id
    Then the company does not get any info
    
    
   Scenario: Contacting client by name that doesnt exists
    Given a registery 
    When the company searches for the client by the non existing name
    Then the company does not get any info
    
   Scenario: Contacting registered client by email
    Given registered client2
    When the company searches for the client by email 
    Then the company gets all the info of the client
    

