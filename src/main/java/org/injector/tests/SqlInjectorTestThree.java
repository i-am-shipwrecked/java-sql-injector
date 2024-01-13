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


public class SqlInjectorTestThree {
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

    @Parameters("appUrl")
    @Test
    public void sqlInjectionTest() {
        String appUrl = ScenarioContext.getAppUrl();
        setup();
        userChooseThePageWhereHeWantsToStartSQLInjectorTests(appUrl);

        String[] injections = {
                "' OR '1'='1'; --",
                "; DROP TABLE users; --",
                "' UNION SELECT table_name FROM information_schema.tables; --",
                "' OR 'x'='x'; --",
                "' AND 'x'='x'; --",
                "' OR 'a'='a'; --",
                "' AND 'a'='a'; --",
                "' OR '1'='1'; --",
                "' AND '1'='1'; --",
                "' OR '123'='123'; --",
                "' AND '123'='123'; --",
                "' OR 'abc'='abc'; --",
                "' AND 'abc'='abc'; --",
                "' OR 'admin'='admin'; --",
                "' AND 'admin'='admin'; --",
                "' OR 1=1; --",
                "' AND 1=1; --",
                "' OR 1=2; --",
                "' AND 1=2; --",
                "' OR 'a'='a'; DROP TABLE users; --",
                "' OR '1'='1'; DROP TABLE users; --",
                "' UNION SELECT username, password FROM users; --",
                "' UNION SELECT null, table_name FROM information_schema.tables; --",
                "' UNION SELECT null, column_name FROM information_schema.columns WHERE table_name='users'; --",
                "' UNION SELECT null, CONCAT(username, ':', password) FROM users; --",
                "' UNION SELECT null, table_schema FROM information_schema.tables; --",
                "' OR EXISTS(SELECT * FROM users WHERE username='admin' AND password LIKE '%pass%'); --",
                "' OR SLEEP(5); --",
                "' OR 1=1 INTO OUTFILE '/tmp/test.txt'; --",
                "' OR 1=1 UNION ALL SELECT null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null FROM information_schema.tables; --"
        };


        for (int i = 0; i < injections.length; i++) {
            System.out.println("Injecting: " + injections[i]);
            userTriesToTypeInASQLInjectionIntoInputField(injections[i % injections.length]);
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
}
