package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;

import org.openqa.selenium.WebDriver;

import static managers.DriverManager.quitDriver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        quitDriver();
    }
}