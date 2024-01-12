package org.injector.tests;

import org.injector.managers.DriverManager;
import org.injector.utils.PageFactory;
import org.injector.utils.Waiter;
import org.openqa.selenium.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Properties;

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
            System.out.println("браузер не закрылся");
        }
    }

    @Test
    public void sqlInjectionTest() {
        setup();
        userChooseThePageWhereHeWantsToStartSQLInjectorTests();

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
        };

        for (int i = 0; i < 10; i++) {
            userTriesToTypeInASQLInjectionIntoInputField(injections[i % injections.length]);
        }

        userClicksOnEnter();
    }

    private void userClicksOnEnter() {

    }

    private void userChooseThePageWhereHeWantsToStartSQLInjectorTests() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("sql_injector.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String appUrl = properties.getProperty("app.url");
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
        inputElement.sendKeys(sqlInjection);
        inputElement.sendKeys(Keys.RETURN);
    }
}
