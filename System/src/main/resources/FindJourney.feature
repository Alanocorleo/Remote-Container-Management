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
Feature: Finding a journey

  @tag1
  Scenario: Find journeys according to origin
    Given 5 containers containing "Fish" registered to journey "CO02292" by client 234 from "Copenhagen" to "Oslo" regulated by "Maersk"
    And 2 containers containing "Flowers" registered to journey "AC02391" by client 234 from "Amsterdam" to "Copenhagen" regulated by "SeaLand"
		And 3 containers containing "Fish" registered to journey "CO02292" by client 67 from "Copenhagen" to "Oslo" regulated by "Maersk"
    When finding based on criteria "origin" specified as "Copenhagen" for client 234
    Then show journeys with origin "Copenhagen" of client 234
   
  @tag2
  Scenario: Find journeys according to destination
    Given 5 containers containing "Fish" registered to journey "CO02292" by client 234 from "Copenhagen" to "Oslo" regulated by "Maersk"
    And 2 containers containing "Flowers" registered to journey "AC02391" by client 234 from "Amsterdam" to "Copenhagen" regulated by "SeaLand"
		And 3 containers containing "Fish" registered to journey "CO02292" by client 67 from "Copenhagen" to "Oslo" regulated by "Maersk"
    When finding based on criteria "destination" specified as "Oslo" for client 234
    Then show journeys with destination "Oslo" of client 234
    
	@tag3
  Scenario: Find journeys according to content-type
    Given 5 containers containing "Fish" registered to journey "CO02292" by client 234 from "Copenhagen" to "Oslo" regulated by "Maersk"
    And 2 containers containing "Flowers" registered to journey "AC02391" by client 234 from "Amsterdam" to "Copenhagen" regulated by "SeaLand"
		And 3 containers containing "Fish" registered to journey "CO02292" by client 67 from "Copenhagen" to "Oslo" regulated by "Maersk"
    When finding based on criteria "content-type" specified as "Fish" for client 234
    Then show journeys with content-type "Fish" of client 234
   
  @tag4
  Scenario: Find journeys according to company
    Given 5 containers containing "Fish" registered to journey "CO02292" by client 234 from "Copenhagen" to "Oslo" regulated by "Maersk"
    And 2 containers containing "Flowers" registered to journey "AC02391" by client 234 from "Amsterdam" to "Copenhagen" regulated by "SeaLand"
		And 3 containers containing "Fish" registered to journey "CO02292" by client 67 from "Copenhagen" to "Oslo" regulated by "Maersk"
    When finding based on criteria "company" specified as "Maersk" for client 234
    Then show journeys with company "Maersk" of client 234
  
  @tag5
  Scenario: Find all journeys
    Given 5 containers containing "Fish" registered to journey "CO02292" by client 234 from "Copenhagen" to "Oslo" regulated by "Maersk"
    And 2 containers containing "Flowers" registered to journey "AC02391" by client 234 from "Amsterdam" to "Copenhagen" regulated by "SeaLand"
		And 3 containers containing "Fish" registered to journey "CO02292" by client 67 from "Copenhagen" to "Oslo" regulated by "Maersk"
    When finding based on criteria "none" specified as "unspecified" for client 234
    Then show all journeys of client 234  
