@Demo @ContactsAPI
Feature: Contacts API

  @API @TC-API-CON-001
  Scenario: Tests the valid addition of a Contact through REST API
    When a system requests the addition of a new contact
      | Name            | Email                    | Phone      |
      | ContactRest 001 | emailRest001@vpetrou.com | 2102222222 |
    Then the system responds and a new Contact "emailRest001@vpetrou.com" is created successfully
