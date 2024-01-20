package org.injector.tests;

import org.injector.managers.DriverManager;
import org.injector.utils.PageFactory;
import org.injector.utils.ScenarioContext;
import org.injector.utils.Waiter;
import org.openqa.selenium.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SqlInjectorTestTwo {
    private WebDriver driver;
    private PageFactory pageFactory;
    private Waiter waiter;

    @BeforeTest
    public void setup() {
        driver = DriverManager.getDriver();
        pageFactory = new PageFactory(driver);
        waiter = Waiter.getInstance();
    }

    @AfterTest
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.out.println("-- Oops, browser issues ='( --");
        }
    }

    @Parameters({"tableName", "appUrl"})
    @Test
    public void sqlInjectionTest() {
        String appUrl = ScenarioContext.getAppUrl();
        String tableName = ScenarioContext.getTableName();

        setup();

        userChooseThePageWhereHeWantsToStartSQLInjectorTests(appUrl);
        String[] injections = generateInjections(tableName);

        for (int i = 0; i < injections.length; i++) {
            System.out.println("Injecting: " + injections[i]);
            userTriesToTypeInASQLInjectionIntoInputField(injections[i]);
        }

        userClicksOnEnter();
    }

    private void userClicksOnEnter() {

    }

    public void userChooseThePageWhereHeWantsToStartSQLInjectorTests(String appUrl) {
        driver.get(appUrl);
    }

    private void userTriesToTypeInASQLInjectionIntoInputField(String sqlInjection) {
        WebElement inputElement;
        try {
            inputElement = driver.findElement(By.cssSelector("input"));
        } catch (NoSuchElementException e) {
            pageFactory.scrollDownToFindInput();

            inputElement = waiter.waitElementToBePresenceOnThePageByCssSelector("input");

            if (inputElement == null) {
                throw new NoSuchElementException("Input field not found after scrolling");
            }
        }
        inputElement.clear();
        inputElement.sendKeys(sqlInjection);
        inputElement.sendKeys(Keys.RETURN);
    }

    private String[] generateInjections(String tableName) {
        return new String[]{
                String.format("SELECT * FROM %s;", tableName),
                String.format("DELETE FROM %s;", tableName),
                String.format("UPDATE %s SET column1='new_value' WHERE condition;", tableName),
                String.format("INSERT INTO %s (column1, column2) VALUES ('value1', 'value2');", tableName),
                String.format("DROP TABLE %s;", tableName),
                String.format("SELECT * FROM %s WHERE column1='some_value' OR 1=1; --", tableName),
                String.format("UNION SELECT column1, column2 FROM %s;", tableName),
                String.format("ALTER TABLE %s ADD COLUMN new_column INT;", tableName),
                String.format("SELECT * FROM %s; DROP TABLE %s; --", tableName, tableName),
                String.format("SELECT * FROM %s; EXEC sp_configure 'show advanced options', 1; RECONFIGURE; --", tableName),
                String.format("SELECT * FROM %s WHERE column1 = 'value' OR '1' = '1';", tableName),
                String.format("INSERT INTO %s (column1) VALUES ('malicious_value'); --", tableName),
                String.format("UPDATE %s SET column1 = 'malicious_value' WHERE condition; --", tableName),
                String.format("DELETE FROM %s WHERE column1 = 'value' OR '1' = '1';", tableName),
                String.format("SELECT * FROM %s WHERE column1 = 'value' UNION SELECT 'injected_value'; --", tableName),
                String.format("CREATE TABLE malicious_table AS SELECT * FROM %s;", tableName),
                String.format("SELECT * FROM %s; DROP PROCEDURE malicious_procedure; --", tableName),
                String.format("EXEC('SELECT * FROM %s'); --", tableName),
                String.format("EXEC xp_cmdshell('command'); --", tableName),
                String.format("DECLARE @sql NVARCHAR(MAX); SET @sql = N'SELECT * FROM %s;'; EXEC sp_executesql @sql;", tableName)
        };
    }

}
