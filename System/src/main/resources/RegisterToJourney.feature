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
Feature: Register client containers to a journey

  @tag1
  Scenario: Register to a journey
    Given journey "CO02292" from "Copenhagen" to "Oslo"
    And 2 registered containers from "Copenhagen"
    And origin "Copenhagen"
    And destination "Oslo"
    When registering to "CO02292"
    Then confirm registering "Container has been assigned to a journey" 014

  @tag2
  Scenario: Deny registering due to unavailable journey
    Given journey "AO02292" from "Amsterdam" to "Oslo"
    And 2 registered containers from "Copenhagen"
    And origin "Copenhagen"
    And destination "Oslo"
    When registering to "CO02292"
    Then deny registering "Journey not found" 140
    
  @tag3
  Scenario: Deny registering due to unavailable container
    Given journey "CO02292" from "Copenhagen" to "Oslo"
    And 0 registered containers from "Copenhagen"
    And origin "Copenhagen"
    And destination "Oslo"
    When registering to "CO02292"
    Then deny registering "Container not found" 111
    
  @tag4
  Scenario: Deny assigning due to unavailable journey because of different origin and destination
    Given journey "CO02292" from "Copenhagen" to "Oslo"
    And 2 registered containers from "Copenhagen"
    And origin "Amsterdam"
    And destination "Oslo"
    When registering to "CO02292"
    Then deny registering "Journey not found" 140
    
  @tag5
  Scenario: Deny assigning due to unavailable journey because of different origin
    Given journey "CO02292" from "Copenhagen" to "Oslo"
    And 2 registered containers from "Copenhagen"
    And origin "Amsterdam"
    And destination "Oslo"
    When registering to "CO02292"
    Then deny registering "Journey not found" 140
    
  @tag6
  Scenario: Deny assigning due to unavailable journey because of different destination
    Given journey "CO02292" from "Copenhagen" to "Oslo"
    And 2 registered containers from "Copenhagen"
    And origin "Copenhagen"
    And destination "Amsterdam"
    When registering to "CO02292"
    Then deny registering "Journey not found" 140