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
Feature: Creating a journey

  @tag1
  Scenario: Successfully create a journey
    Given origin "Copenhagen"
    And destination "Oslo"
    And departure date "30/04/2021"
    And arrival date "31/04/2021"
    When creating journey 
    Then confirm journey creation "Journey has been created" 020 
    And assign a journey-ID
    
  @tag2
  Scenario: Deny journey creation due to missing parameters
    When creating journey 
    Then deny journey creation "Necessary parameters are not entered" 210
  
  @tag3
  Scenario: Deny journey creation due to missing origin
    Given destination "Oslo"
    And departure date "30/04/2021"
    And arrival date "31/04/2021"
    When creating journey 
    Then deny journey creation "Necessary parameters are not entered" 210
  
  @tag4
  Scenario: Deny journey creation due to missing destination
    Given origin "Copenhagen"
    And departure date "30/04/2021"
    And arrival date "31/04/2021"
    When creating journey 
    Then deny journey creation "Necessary parameters are not entered" 210
  
  @tag5
  Scenario: Deny journey creation due to missing departure date
    Given origin "Copenhagen"
    And destination "Oslo"
    And arrival date "31/04/2021"
    When creating journey 
    Then deny journey creation "Necessary parameters are not entered" 210
  
  @tag6
  Scenario: Deny journey creation due to missing arrival date
    Given origin "Copenhagen"
    And destination "Oslo"
    And departure date "30/04/2021"
    When creating journey 
    Then deny journey creation "Necessary parameters are not entered" 210
  