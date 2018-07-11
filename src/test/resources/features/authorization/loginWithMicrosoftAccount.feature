@Demo

Feature: Getting through Microsoft Authorization to the T-Mobile DIGITS dashboard.

  Background: Getting to the test system
    Given I navigate to the "Demo Server"
    When I see "Digits" login screen
    Then I press "Microsoft" button

  Scenario: Go through authorization and get the application dashboard, by selecting: Share Location
    Then I get through "Microsoft" authorization with credentials:
      | Tmostg02@outlook.com | Tmotest00 |
    When I see loading screen
    And I sit on the Page for "10" seconds

  Scenario: Go through authorization and get stuck on loading screen, by selecting
    Then I get through "Microsoft" authorization with credentials:
      | Tmostg02@outlook.com | Tmotest00 |
    When I see loading screen
    And I sit on the Page for "10" seconds



