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
Feature: Complete and remove journey from registry

  @tag1
  Scenario: Complete a journey and remove it from the registry
  	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "30/04/2021" and arriving "31/04/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "20/05/2021" and arriving "21/05/2021"
    When completing journey "CO02292"
    Then confirm completing and removing of the journey "Journey has been completed and successfully removed" 021
    
  @tag2
  Scenario: Deny completion because the journey was not found
    When completing journey "CO02292"
    Then deny completing "Journey is not found" 120

  @tag3
  Scenario: Deny completion because the journey was not found
  	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "30/04/2021" and arriving "31/04/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "20/05/2021" and arriving "21/05/2021"
    When completing journey "CO00002"
    Then deny completing "Journey is not found" 120
    
  @tag4
  Scenario: Remove a container from a journey
  	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "30/04/2021" and arriving "31/04/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "20/05/2021" and arriving "21/05/2021"
    When removing a container with ID 2 from journey "CO02292"
    Then confirm container removing "Container has been successfully removed" 011
    
  @tag5
  Scenario: Deny completion because the journey was not found
    When removing a container with ID 2 from journey "CO02292"
    Then deny container removing "Journey is not found" 120

  @tag6
  Scenario: Deny completion because the journey was not found
  	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "30/04/2021" and arriving "31/04/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "20/05/2021" and arriving "21/05/2021"
    When removing a container with ID 2 from journey "CO00002"
    Then deny container removing "Journey is not found" 120
    
  @tag7
  Scenario: Deny completion because the journey was not found
  	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "30/04/2021" and arriving "31/04/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "20/05/2021" and arriving "21/05/2021"
    When removing a container with ID 12 from journey "CO02292"
    Then deny container removing "Container is not found" 110