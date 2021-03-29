#Author: Group 2
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
Feature: Registering a container

	@tag1
  Scenario: Register a container, create a journey, and put it on record successfuly
  	Given client
  	And container
    And origin "Copenhagen"
    And destination "Oslo"
    And content-type "Fish"
    And company "Maersk"
    When registering
    Then confirm registration
    And create journey-ID "CO00001"
    And put on record
  
  @tag2
  Scenario: Deny updating due to missing origin, destination, company or content type
  	Given client
  	And container
    When registering
    Then deny registration
   
  @tag3
  Scenario: Deny updating update due to missing origin
  	Given client
  	And container
    And destination "Oslo"
    And content-type "Fish"
    And company "Maersk"
    When registering
    Then deny registration
    
  @tag4
  Scenario: Deny updating due to missing destination
  	Given client
  	And container
    And origin "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    When registering
    Then deny registration
    
  @tag5
  Scenario: Deny updating due to missing content-type
  	Given client
  	And container
    And origin "Copenhagen"
    And destination "Oslo"
    And company "Maersk"
    When registering
    Then deny registration
  
  @tag6
  Scenario: Deny updating due to missing company
  	Given client
  	And container
    And origin "Copenhagen"
    And destination "Oslo"
    And content-type "Fish"
    When registering
    Then deny registration
 