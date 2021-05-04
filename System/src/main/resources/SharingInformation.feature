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
Feature: Sharing information
  I registed client wants to share their information with another client and the other client wants to see the registed client's information

  @tag1
  Scenario: Send your information to another client successfully 
    Given two registered clients2
    When one client sends his information to the other client
   Then the information is successfully sent
    
  @tag1
  Scenario: Send your information to another client unsuccessfully 
    Given one registered client and his unregistered friend
    When one client sends his information to the unregistered client
    Then the information is not successfully sent
    
  @tag2
  Scenario: being able to access friend's shared information
    Given two registered clients2
    When one client tries to access his friend's information
    Then the information received successfully