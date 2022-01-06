Feature: As a user i want to know if server is up and running

    Scenario: Check the api status
        When I send a GET request to /health-check
        Then the status code should be: 200