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


public class SqlInjectorTest  {
    private WebDriver driver;
    private PageFactory pageFactory;
    private Waiter waiter;
@Autowired
    private ScenarioContext scenarioContext;

    @BeforeTest
    public void setup() {
        System.out.println("trying to setup browser");
        driver = DriverManager.getDriver();
        pageFactory = new PageFactory(driver);
        waiter = Waiter.getInstance();
        System.out.println("browser is setuped");
        }

    @AfterTest
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.out.println("браузер не закрылся");
        }
    }
    @Parameters("appUrl")
    @Test
    public void sqlInjectionTest() {
        System.out.println("111111111111111111111111111111111111111111111111111111111");
        String appUrl = ScenarioContext.getAppUrl();
        setup();
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

        for (int i = 0; i < 10; i++) {
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
        inputElement.sendKeys(sqlInjection);
        inputElement.sendKeys(Keys.RETURN);
    }
}
