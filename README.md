# SQL Injection

SQL injection, also known as SQLI, is a common attack vector that uses malicious SQL code for backend database manipulation to access information that was not intended to be displayed. This information may include any number of items, including sensitive company data, user lists or private customer details.

## To run tests



```bash
mvn test
```

![SQL-injection-needle](https://github.com/i-am-shipwrecked/java-sql-injector/assets/80786579/4443bfaa-8802-49ee-8df6-85291dfb7a1e)

# API Endpoints:
---
***POST*** `http://localhost:8080/runTests/level1`

_Request body:_
```json

  {
    "url": "https://www.example.com/"
  }

```
* Running tests with 10 SQL injections.
---
***POST*** `http://localhost:8080/runTests/level2`

_Request body:_
```json

  {
    "url": "https://www.example.com/"
  }

```
* Running tests with 20 SQL injections.
---

***POST*** `http://localhost:8080/runTests/level3`

_Request body:_
```json

  {
    "url": "https://www.example.com/"
  }

```
* Running tests with 30 SQL injections.
---
