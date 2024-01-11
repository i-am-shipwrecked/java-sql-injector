package org.injector.tests;

import io.cucumber.java.Before;
import org.injector.managers.DriverManager;
import org.injector.utils.PageFactory;
import org.injector.utils.Waiter;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.Properties;



public class SqlInjectorTest {
    private WebDriver driver;
    private PageFactory pageFactory;
    private Waiter waiter;

    @Before
    public void setup() {
        driver = DriverManager.getDriver();
        pageFactory = new PageFactory(driver);
        waiter = Waiter.getInstance();
    }

    @After
    public void tearDown() {
        try {
            DriverManager.quitDriver();
        } catch (Exception e) {
            System.out.println("браузер не закрылся");
        }
    }

    @Test
    public void sqlInjectionTest() {
        setup();  // Убедимся, что setup() вызывается перед тестовым методом
        // Логика теста
        userChooseThePageWhereHeWantsToStartSQLInjectorTests();
        userTriesToTypeInASQLInjectionIntoInputField("your_sql_injection_here");
        userClicksOnEnter();
        // Ваша проверка результата теста
    }

    private void userClicksOnEnter() {
        // Логика клика на Enter
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
        // Логика ввода SQL-инъекции
    }
}
