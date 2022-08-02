Feature: Add Member

  @web
  Scenario: Verify Add Member successfully
    Given I open a web browser
    When I go to web url
      And I click on tab "Add Member"
      And I add member with information
        | FirstName | LastName | Title | Company |  PhoneNumber  |    WebURL               |      Email              |
        | Quoc      | La       | QA    | Contour |  +84902238175 | https://contour.network | quangquoc1804@gmail.com |
      And I click on tab "Home"
    Then I verify member has been added successfully with information
      | FirstName | LastName |
      | Quoc      | La       |

