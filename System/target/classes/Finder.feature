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
Feature: Finding a journey

  @tag1
  Scenario: Find journeys according to origin
    Given client
    And recorded journeys
    When finding based on criteria "origin" specified as "Copenhagen"
    Then show journeys with origin "Copenhagen"
   
  @tag2
  Scenario: Find journeys according to destination
    Given client
    And recorded journeys
    When finding based on criteria "destination" specified as "Oslo"
    Then show journeys with destination "Oslo"
   
  @tag3
  Scenario: Find journeys according to content-type
    Given client
    And recorded journeys
    When finding based on criteria "content-type" specified as "Flowers"
    Then show journeys with content-type "Flowers"
   
  @tag4
  Scenario: Find journeys according to company
    Given client
    And recorded journeys
    When finding based on criteria "company" specified as "Maersk"
    Then show journeys with company "Maersk" 
  
  @tag5
  Scenario: Find all journeys
    Given client
    And recorded journeys
    When finding based on criteria "none" specified as "unspecified"
    Then show all client journeys 