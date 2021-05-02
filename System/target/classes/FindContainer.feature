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
Feature: Finding containers

 	@tag1
  Scenario: Find containers according to container-ID
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "containerID" specified as 2
    Then show containers with container-ID 2

 	@tag2
  Scenario: Find containers according to client-ID
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "owner" specified as 234
    Then show containers with client-ID 234

 	@tag3
  Scenario: Find containers according to position
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "position" specified as "Copenhagen"
    Then show containers with position "Copenhagen"

 	@tag4
  Scenario: Find containers according to content-type
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "contentType" specified as "Fish"
    Then show containers with content-type "Fish"
    
 	@tag5
  Scenario: Find containers according to company
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "company" specified as "Botanica"
    Then show containers with company "Botanica"
    
  @tag6
  Scenario: Find containers according to journey-ID
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "journeyID" specified as "CO02292"
    Then show containers with journey-ID "CO02292"
    
	@tag7
  Scenario: Find containers according to availability
		Given 5 containers containing "Plants" registered by client 234 regulated by "Botanica" to journey "CO02292" positioned in "Copenhagen"
    And 2 containers containing "Flowers" registered by client 234 regulated by "Botanica" to journey "AC02391" positioned in "Esbjerg"
		And 3 containers containing "Fish" registered by client 67 regulated by "CaptainFish" to journey "CO02292" positioned in "Copenhagen"
    When finding containers based on criteria "availability" specified as false
    Then show all occupied containers