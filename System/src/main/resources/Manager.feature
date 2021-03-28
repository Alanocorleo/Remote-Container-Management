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
  	Given company manager "Maersk"
  	And recorded journey
    When updating containers current position of journey "CO00001" to "Gothenburg" 
    Then change position

  @tag2
  Scenario: Deny an update because the journey was not found
  	Given company manager "Maersk"
    When updating containers current position of journey "CO00001" to "Gothenburg" 
    Then deny update
    
  @tag3
  Scenario: Complete a journey and remove it from the record
  	Given company manager "Maersk"
  	And recorded journey
    When completing journey "CO00001"
    Then remove from record
    
  @tag4
  Scenario: Deny completion because the journey was not found
  	Given company manager "Maersk"
    When completing journey "CO00001"
    Then deny removal

