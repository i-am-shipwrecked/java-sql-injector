package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class SqlInjectorSteps {
    private WebDriver driver = DriverManager.getDriver();
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
        List<WebElement> inputElements = driver.findElements(By.tagName("input"));

        for (WebElement inputElement : inputElements) {
            inputElement.sendKeys(sqlInjection);
        }

        WebElement formElement = driver.findElement(By.tagName("form"));
        formElement.submit();
    }

    @And("User clicks on Enter button")
    public void userClicksOnEnter() {

    }

    @Then("Verify that your database is not broken =)")
    public void nothingHappens() {
    }

}
