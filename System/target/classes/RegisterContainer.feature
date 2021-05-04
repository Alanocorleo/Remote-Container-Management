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
Feature: Registering a container

  @tag1
  Scenario: Successfully register a container 
    Given location "Copenhagen"
    When registering container
    Then confirm registration "Container has been registered" 010

  @tag2
  Scenario: Deny registering a container due to missing location
    When registering container
    Then deny registration "Location is not valid" 162