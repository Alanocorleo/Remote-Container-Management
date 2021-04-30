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
Feature: Adding information to containers
  

  @tag1
  Scenario: Successful addition of info
    Given a container with id 100 
    And a logisitc company "Maersk" 
    When logisitc company chooses the container with journey id 100
    And add Temperature 101 
    And add Humidity 102 
    And add Pressure 103 
    And add Position "Copenhagen"  
    Then update container's values
	
	@tag2
  Scenario: adding info to a non-existent container
    Given a logisitc company "Maersk"
    And a container with id 100 
    When logisitc company chooses the container with journey id 200 
    Then show message: container does not exist