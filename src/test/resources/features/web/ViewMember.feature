Feature: View Member

  @web
  Scenario: Verify Add Member successfully
    Given I open a web browser
    When I go to web url
      And I click on tab "View Member"
      And I search with id "1"
    Then I verify user can view member with information
      | FirstName | LastName |    Title    | Company | PhoneNumber  |         WebURL          |      Email                  |
      | Lenox     | Lutfi    | QA Engineer | Contour | 123-456-7890 | https://contour.network | lenox.lutfi@contour.networ  |