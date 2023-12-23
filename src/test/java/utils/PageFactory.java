package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PageFactory {
    private WebDriver driver;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollDownToFindInput() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
