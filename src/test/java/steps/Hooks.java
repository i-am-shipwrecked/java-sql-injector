package steps;

import managers.DriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
