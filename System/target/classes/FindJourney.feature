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
Feature: Finding journeys

 	@tag1
  Scenario: Find client journeys according to journey-ID
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "journeyID" specified as "CO02292" for client 234
    Then show journeys with journey-ID "CO02292" of client 234

  @tag2
  Scenario: Find client journeys according to origin
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "origin" specified as "Copenhagen" for client 234
    Then show journeys with origin "Copenhagen" of client 234
   
  @tag3
  Scenario: Find client journeys according to destination
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "destination" specified as "Oslo" for client 234
    Then show journeys with destination "Oslo" of client 234
    
  @tag4
  Scenario: Find client journeys according to departure date
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "departureDate" specified as "30/04/2021" for client 234
    Then show journeys with departure date "30/04/2021" of client 234
  
  @tag5
  Scenario: Find client journeys according to arrival date
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "arrivalDate" specified as "31/04/2021" for client 234
    Then show journeys with arrival date "31/04/2021" of client 234
    
 	@tag6
  Scenario: Find journeys according to journey-ID
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "journeyID" specified as "CO02292"
    Then show journeys with journey-ID "CO02292"

  @tag7
  Scenario: Find journeys according to origin
		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "origin" specified as "Copenhagen"
    Then show journeys with origin "Copenhagen"
   
  @tag8
  Scenario: Find journeys according to destination
 		Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "destination" specified as "Oslo"
    Then show journeys with destination "Oslo"
    
  @tag9
  Scenario: Find journeys according to departure date
  	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "departureDate" specified as "30/04/2021"
    Then show journeys with departure date "30/04/2021"
  
  @tag10
  Scenario: Find journeys according to arrival date
   	Given 5 containers registered by client 234 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    And 2 containers registered by client 234 to journey "AC02391" from "Amsterdam" to "Copenhagen" departing "20/05/2021" and arriving "21/05/2021"
		And 3 containers registered by client 67 to journey "CO02292" from "Copenhagen" to "Oslo" departing "30/04/2021" and arriving "31/04/2021"
    When finding based on criteria "arrivalDate" specified as "31/04/2021"
    Then show journeys with arrival date "31/04/2021"