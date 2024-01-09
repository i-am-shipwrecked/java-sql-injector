package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;

import org.openqa.selenium.WebDriver;


public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.out.println("Oops");
        }
    }
}
