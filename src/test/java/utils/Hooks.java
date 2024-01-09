package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;

import org.openqa.selenium.WebDriver;


public class Hooks {
    private WebDriver driver;
    private static TestContext testContext = new TestContext();

    @Before
    public void setup() {
        System.out.println("Hello World !!!!!!!!!!");
        driver = DriverManager.getDriver();
    }

   @After
    public void tearDown() {
       System.out.println("Hello World !!!!!!!!!!");
        driver = (WebDriver) testContext.getContext("driver");
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.out.println("браузер не закрылся");
        }
    }
}
