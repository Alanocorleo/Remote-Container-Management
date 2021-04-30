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
Feature: Updating containers information

  @tag1
  Scenario: Update the current position of a container 
  	Given 8 containers registered by client 234 to journey "CO02292" positioned in "Copenhagen"
  	And 2 containers registered by client 67 to journey "AC02391" positioned in "Amsterdam"
    When updating containers current position with current journey "CO02292" to "Gothenburg"
    Then confirm position updating "Position has been updated" 070

  @tag2
  Scenario: Deny updating because the container was not found
    When updating containers current position with current journey "CO02292" to "Gothenburg"
    Then deny position updating "Container is not found" 110
    
  @tag3
  Scenario: Deny updating because the container was not found
  	Given 8 containers registered by client 234 to journey "CO02292" positioned in "Copenhagen"
  	And 2 containers registered by client 67 to journey "AC02391" positioned in "Amsterdam"
    When updating containers current position with current "CO02292" to "Gothenburg"
    Then deny position updating "Container is not found" 110
    