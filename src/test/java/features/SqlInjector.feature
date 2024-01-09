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
      | sqlInjection                                                                                                                                                                                                                                    |
      | ' OR '1'='1'; --                                                                                                                                                                                                                                |
#      | '; DROP TABLE users; --                                                                                                                                                                                                                         |
#      | ' UNION SELECT table_name FROM information_schema.tables; --                                                                                                                                                                                    |
#      | ' OR 'x'='x'; --                                                                                                                                                                                                                                |
#      | ' AND 'x'='x'; --                                                                                                                                                                                                                               |
#      | ' OR 'a'='a'; --                                                                                                                                                                                                                                |
#      | ' AND 'a'='a'; --                                                                                                                                                                                                                               |
#      | ' OR '1'='1'; --                                                                                                                                                                                                                                |
#      | ' AND '1'='1'; --                                                                                                                                                                                                                               |
#      | ' OR '123'='123'; --                                                                                                                                                                                                                            |
#      | ' AND '123'='123'; --                                                                                                                                                                                                                           |
#      | ' OR 'abc'='abc'; --                                                                                                                                                                                                                            |
#      | ' AND 'abc'='abc'; --                                                                                                                                                                                                                           |
#      | ' OR 'admin'='admin'; --                                                                                                                                                                                                                        |
#      | ' AND 'admin'='admin'; --                                                                                                                                                                                                                       |
#      | ' OR 1=1; --                                                                                                                                                                                                                                    |
#      | ' AND 1=1; --                                                                                                                                                                                                                                   |
#      | ' OR 1=2; --                                                                                                                                                                                                                                    |
#      | ' AND 1=2; --                                                                                                                                                                                                                                   |
#      | ' OR 'a'='a'; DROP TABLE users; --                                                                                                                                                                                                              |
#      | ' OR '1'='1'; DROP TABLE users; --                                                                                                                                                                                                              |
#      | ' UNION SELECT username, password FROM users; --                                                                                                                                                                                                |
#      | ' UNION SELECT null, table_name FROM information_schema.tables; --                                                                                                                                                                              |
#      | ' UNION SELECT null, column_name FROM information_schema.columns WHERE table_name='users'; --                                                                                                                                                   |
#      | ' UNION SELECT null, CONCAT(username, ':', password) FROM users; --                                                                                                                                                                             |
#      | ' UNION SELECT null, table_schema FROM information_schema.tables; --                                                                                                                                                                            |
#      | ' OR EXISTS(SELECT * FROM users WHERE username='admin' AND password LIKE '%pass%'); --                                                                                                                                                          |
#      | ' OR SLEEP(5); --                                                                                                                                                                                                                               |
#      | ' OR 1=1 INTO OUTFILE '/tmp/test.txt'; --                                                                                                                                                                                                       |
#      | ' OR 1=1 UNION ALL SELECT null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null FROM information_schema.tables; -- |

