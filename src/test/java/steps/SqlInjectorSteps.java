package steps;

import config.LoggerConfigurator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.DriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utils.PageFactory;
import utils.Waiter;

import java.io.InputStream;
import java.util.Properties;


public class SqlInjectorSteps {
    private WebDriver driver = DriverManager.getDriver();
    private static final Logger LOGGER = LoggerConfigurator.getLogger();
    PageFactory pageFactory = new PageFactory(driver);
    Waiter waiter = Waiter.getInstance();

    @Given("User is on the page, which you can insert into sql_injector.properties")
    public void userIsOnThePageWhichYouCanInsertIntoSql_injectorProperties() {
    }

    @Given("User choose the page where he wants to start SQL injector tests")
    public void userChooseThePageWhereHeWantsToStartSQLInjectorTests() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("sql_injector.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String appUrl = properties.getProperty("app.url");
        driver.get(appUrl);
    }

    @When("User tries to type in a SQL Injection {string} into input field")
    public void userTriesToTypeInASQLInjectionIntoInputField(String sqlInjection) {
        WebElement inputElement;

        try {
            inputElement = driver.findElement(By.cssSelector("input"));
        } catch (NoSuchElementException e) {
            LOGGER.info("Input field not found. Scrolling down to find it.");
            pageFactory.scrollDownToFindInput();

            inputElement = waiter.waitElementToBePresenceOnThePageByCssSelector("input");

            if (inputElement == null) {
                LOGGER.error("Input field not found even after scrolling. Breaking the test.");
                throw new NoSuchElementException("Input field not found after scrolling");
            }
        }

        inputElement.sendKeys(sqlInjection);
        LOGGER.info("Trying to break db with " + sqlInjection);
        inputElement.sendKeys(sqlInjection, Keys.RETURN);
    }

    @And("User clicks on Enter button")
    public void userClicksOnEnter() {

    }

    @Then("Verify that your database is not broken =)")
    public void nothingHappens() {
    }
}
