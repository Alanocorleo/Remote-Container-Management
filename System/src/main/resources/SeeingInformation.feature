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
Feature: Seeing the info of containers
  

  @tag1
  Scenario: Successful seeing of info
    Given a container with id 100 and owner 1000
    And a client with id 1000 
    When requesting to see info for container 100
    Then history of container is shown
    
    @tag2
  Scenario: UnSuccessful seeing of info because container does not belong to the client
    Given a container with id 100 and owner 1000
    And a client with id 2000 
    When requesting to see info for container 100
    Then history of container is not shown

