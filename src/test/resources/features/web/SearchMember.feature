Feature: Search Member

  @web
  Scenario: Verify Add Member successfully
    Given I open a web browser
    When I go to web url
      And I click on tab "Search Member"
      And I search with text "Quoc"
    Then I verify the table contain text "Quoc" in all rows

