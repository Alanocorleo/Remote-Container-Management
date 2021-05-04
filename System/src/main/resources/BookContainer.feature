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
Feature: Booking containers

	@tag1
  Scenario: Book containers
  	Given location "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When booking
    Then confirm booking "Container has been registered" 010
  
  @tag2
  Scenario: Book fewer containers than requested
  	Given location "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    And number of containers 5
    And shipping yard in "Copenhagen" with 3 containers
    When booking
    Then confirm booking "Container has been registered" 010
    
  @tag3
  Scenario: Deny booking due to unavailable containers
    Given location "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    And number of containers 5
    When booking
    Then deny booking "Container is not found" 110
    
  @tag4
  Scenario: Deny booking due to missing content type
  	Given location "Copenhagen"
    And company "Maersk"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When booking
    Then deny booking "Necessary parameters are not entered" 210
    
  @tag5
  Scenario: Deny booking due to missing company
  	Given location "Copenhagen"
    And content-type "Fish"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When booking
    Then deny booking "Necessary parameters are not entered" 210
    
  @tag6
  Scenario: Deny booking due to missing company and content-type
 	  Given location "Copenhagen"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When booking
    Then deny booking "Necessary parameters are not entered" 210