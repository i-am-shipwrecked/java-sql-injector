# SQL Injection

SQL injection, also known as SQLI, is a common attack vector that uses malicious SQL code for backend database manipulation to access information that was not intended to be displayed. This information may include any number of items, including sensitive company data, user lists or private customer details.

---
## To start application



```bash
mvn spring-boot:run
```

---
## To run tests



```bash
mvn test
```

![SQL-injection-needle](https://github.com/i-am-shipwrecked/java-sql-injector/assets/80786579/4443bfaa-8802-49ee-8df6-85291dfb7a1e)

---
# Run Database Locally

To run the database locally, follow these steps:

1. Open a terminal or Git Bash in the project's root folder.

2. Build the Docker image:
    ```bash
    docker-compose build
    ```

3. Start the Docker container in the background:
    ```bash
    docker-compose up -d
    ```

4. Launch the Docker container:
    ```bash
    docker start postgres
    ```

5. Check the container status:
    ```bash
    docker container ls -a
    ```

Ensure that the database is successfully started and ready for use.

---

# API Endpoints:
---
***POST*** `http://localhost:8080/runTests/level1`

_Request body:_
```json

  {
    "url": "https://www.example.com/"
  }

```

_Response body:_

```json
{
  Sql injection with EASY load is - DONE
}
```

_Response status:_
```
200
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
_Response body:_

```json
{
  Sql injection with MID load is - DONE
}
```

_Response status:_
```
200
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
_Response body:_

```json
{
  Sql injection with HARD load is - DONE
}
```

_Response status:_
```
200
```
* Running tests with 30 SQL injections.
---
