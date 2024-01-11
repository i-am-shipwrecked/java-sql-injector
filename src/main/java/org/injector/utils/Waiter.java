package org.injector.utils;

import org.injector.managers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    private final int TIMEOUT_IN_SECONDS = 10;
    private WebDriverWait wait;

    private static class Holder {
        private static final Waiter INSTANCE = new Waiter();
    }

    private Waiter() {
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT_IN_SECONDS));
    }

    public static Waiter getInstance() {
        return Holder.INSTANCE;
    }

    public WebElement waitElementToBePresenceOnThePageByCssSelector(String cssSelector) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }
}
