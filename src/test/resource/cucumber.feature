Feature: API Testing for Knime

  Scenario: Test create and delete space function
    Given I load all necessary data
    And I check the spaceDetails
    And I create new space
    And I verify new space is created
    Then I delete new space
    And I verify new space is deleted
