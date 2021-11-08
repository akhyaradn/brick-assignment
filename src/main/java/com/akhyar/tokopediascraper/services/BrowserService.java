package com.akhyar.tokopediascraper.services;

import com.akhyar.tokopediascraper.tokopedia.TokopediaUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BrowserService {
    private static final String USER_AGENT =
            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36";

    private static final String WINDOW_OPEN = "window.open()";
    private static final String SCROLL_300 = "window.scrollBy(0,300)";
    private static final String SCROLL_600 = "window.scrollBy(0,600)";

    private static final String EMPTY = "";

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    public BrowserService() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments(USER_AGENT);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 5);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public List<String> initializeTabs() {
        driver.get(TokopediaUrl.BASE_URL);
        jsExecutor.executeScript(WINDOW_OPEN);
        return new ArrayList<>(driver.getWindowHandles());
    }

    public void getPageDocument(String path, String tab) {
        switchTab(tab);
        driver.get(path);
    }

    public List<WebElement> getUrlHandphones(String url, String xpath, String tab) {
        switchTab(tab);
        driver.get(url);
        jsExecutor.executeScript(SCROLL_600);
        return driver.findElements(By.xpath(xpath));
    }

    public void waitToFinishLoad(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void scroll300() {
        jsExecutor.executeScript(SCROLL_300);
    }

    public String getText(String xpath) {
        return driver.findElements(By.xpath(xpath)).isEmpty()
                ? EMPTY : driver.findElement(By.xpath(xpath)).getText();
    }

    public String getText(String xpath, String attribute) {
        return driver.findElements(By.xpath(xpath)).isEmpty()
                ? EMPTY : driver.findElement(By.xpath(xpath)).getAttribute(attribute);
    }

    public void switchTab(String tab) {
        driver.switchTo().window(tab);
    }

    public void quit() {
        driver.quit();
    }
}
