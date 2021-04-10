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
Feature: Registering containers

	@tag1
  Scenario: Register containers
  	Given location "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When registering
    Then confirm registering "Container has been registered" 010
  
  @tag2
  Scenario: Register fewer containers than requested
  	Given location "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    And number of containers 5
    And shipping yard in "Copenhagen" with 3 containers
    When registering
    Then confirm registering "Container has been registered" 010
    
  @tag3
  Scenario: Deny registering due to unavailable containers
    Given location "Copenhagen"
    And content-type "Fish"
    And company "Maersk"
    And number of containers 5
    When registering
    Then deny registering "Container not found" 111
    
  @tag4
  Scenario: Deny registering due to missing content type
  	Given location "Copenhagen"
    And company "Maersk"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When registering
    Then deny registering "Necessary parameters not entered" 110
    
  @tag5
  Scenario: Deny registering due to missing company
  	Given location "Copenhagen"
    And content-type "Fish"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When registering
    Then deny registering "Necessary parameters not entered" 110
    
  @tag6
  Scenario: Deny registering due to missing company and content-type
 	  Given location "Copenhagen"
    And number of containers 5
    And shipping yard in "Copenhagen" with 20 containers
    When registering
    Then deny registering "Necessary parameters not entered" 110
 