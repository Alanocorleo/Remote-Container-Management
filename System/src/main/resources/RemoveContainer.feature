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
Feature: Remove container from registry

  @tag1
  Scenario: Successfully remove the selected container 
    Given a container with ID 22
    And a container with ID 77
    And a container with ID 200
    When removing container number 1
    Then confirm removing of a container with ID 77
 
  @tag2
  Scenario: Successfully remove the selected container 
    Given a container with ID 22
    And a container with ID 77
    And a container with ID 200
    When removing container number 2
    And removing container number 1
    And removing container number 0
    Then confirm removing of a container with ID 200
    Then confirm removing of a container with ID 77
    Then confirm removing of a container with ID 22
    
  @tag3
  Scenario: Restrict removing when there is nothing to select
    When removing container number 1
    Then pass