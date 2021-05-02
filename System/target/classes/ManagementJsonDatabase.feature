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
Feature: Management of Json databases

	@tag1
  Scenario: Successfully produce program databases
    When producing client, container and journey databases
    Then confirm producing
    
  @tag2
  Scenario: Successfully update program databases
  	Given client, container and journey databases
    And unsaved data
    When saving
    Then confirm database updates
 
  @tag3
  Scenario: Successfully produce and update program databases in case if they do not exist when updating 
  	Given unsaved data
    When saving
    Then confirm database updates
 
  @tag4
  Scenario: Successfully produce empty program databases when saving without data
    When saving
    Then confirm producing
    
  @tag5
  Scenario: Unsuccessful pull when the databases do not exist 
    When pulling
    Then fail