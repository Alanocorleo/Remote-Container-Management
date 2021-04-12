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
Feature: Update Json database
  I want to use this template for my feature file

  @tag1
  Scenario: Successful update of Json file
    Given unsaved data
    And a Json file "journey_database.json"
    When Saving to Json file
    Then Confirm update

  @tag2
  Scenario: Unsuccessful update due to missing Json file
    Given unsaved data
    When Saving to Json file
    Then Deny update
    
  @tag3
  Scenario: Successful deletion of data from Json file
    Given information to be removed
    And a Json file "journey_database.json"
    When Deleting from Json file
    Then Confirm deletion
    
  @tag4
  Scenario: Unsuccessful deletion due to missing Json file
    Given information to be removed
    When Deleting from Json file
    Then Deny deletion
    
  @tag5
  Scenario: Unsuccessful deletion due to information not existing in Json file
    Given information to be removed that does not exist in given Json file
    And a Json file "journey_database.json"
    When Deleting from Json file
    Then Deny deletion
