@Demo

Feature: Getting through Google Authorization to the T-Mobile DIGITS dashboard.

  Scenario: Getting to the test system
    Given I navigate to the "Demo Server"
    When I see "Digits" login screen
    Then I press "Google" button
    And I sit on the Page for "10" seconds


