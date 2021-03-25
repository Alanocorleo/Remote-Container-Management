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
  Scenario: Successful registration
    Given client-ID
    And origin "Copenhagen"
    And destination "Oslo"
    And content-type "Fish"
    And company "Maersk"
    When registering
    Then confirm the registration
    And create a journey-ID "CO00001"
    And put on record
  
  @tag2
  Scenario: Missing origin, destination, company or content type
  	Given client-ID
    When registering
    Then system displays a message that entry information is missing
    
  @tag3
  Scenario: Successful registration
    Given client-ID
    And origin "Copenhagen"
    And destination "Oslo"
    When registering
    Then system displays a message that entry information is missing
    
   @tag3
  Scenario: Successful registration
    Given client-ID
    And origin "Copenhagen"
    And company "Maersk"
    When registering
    Then system displays a message that entry information is missing
  