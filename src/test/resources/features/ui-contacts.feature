@Demo @ContactsUI
Feature: Contacts UI

  @UI @TC-UI-CON-001
  Scenario: Tests the valid addition of a Contact
    Given the Demo Application is opened
    When the user navigates to "Add New Contact" page
    And the user adds a new contact
      | Name        | Email                | Phone |
      | Contact 001 | email001@vpetrou.com | 2101111111      |
    Then a new Contact "email001@vpetrou.com" is created successfully

