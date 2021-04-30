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
Feature: Managing journeys information

  @tag1
  Scenario: Update the current position of a container 
    Given logistic company "Maersk"
  	And 5 containers registered to journey "CO02292" from "Copenhagen" to "Oslo" regulated by "Maersk" 
    When updating containers current position of journey "CO02292" to "Gothenburg" 
    Then confirm updating "Position has been updated" 070

  @tag2
  Scenario: Deny updating because the journey was not found
  	Given logistic company "Maersk"
    When updating containers current position of journey "CO02292" to "Gothenburg"
    Then updating "Journey not found" 140
    
  @tag3
  Scenario: Deny updating because the journey was not found 
    Given logistic company "Maersk"
  	And 5 containers registered to journey "CO02292" from "Copenhagen" to "Oslo" regulated by "Maersk" 
    When updating containers current position of journey "CO00002" to "Gothenburg" 
    Then confirm updating "Journey not found" 140
    
  @tag4
  Scenario: Complete a journey and remove it from the record
  	Given logistic company "Maersk"
  	And 5 containers registered to journey "CO02292" from "Copenhagen" to "Oslo" regulated by "Maersk" 
    When completing journey "CO02292"
    Then remove from journey database
    
  @tag5
  Scenario: Deny completion because the journey was not found
  	Given logistic company "Maersk"
    When completing journey "CO02292"
    Then deny removal

