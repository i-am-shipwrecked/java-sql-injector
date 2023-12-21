Feature: SQL injector

  Background: Your web-page is available
    Given User choose the page where he wants to start SQL injector tests

  @SQL-Injections @Security-Testing @Negative @Automation @Run
  Scenario Outline: SQL Injections
    Given User is on the page, which you can insert into sql_injector.properties
    When User tries to type in a SQL Injection "<sqlInjection>" into input field
    And User clicks on Enter button
    Then Verify that your database is not broken =)

    Examples:
      | sqlInjection                                                 |
      | ' OR '1'='1'; --                                             |
      | '; DROP TABLE users; --                                      |
      | ' UNION SELECT table_name FROM information_schema.tables; -- |
