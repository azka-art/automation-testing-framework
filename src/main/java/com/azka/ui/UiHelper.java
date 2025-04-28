package com.azka.ui;

import com.azka.util.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Helper class for UI testing
 */
public class UiHelper {
    private static final Logger log = LogManager.getLogger(UiHelper.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private final ConfigManager config;
    
    public UiHelper() {
        config = ConfigManager.getInstance();
        initDriver();
    }
    
    private void initDriver() {
        String browser = config.getBrowser().toLowerCase();
        boolean headless = config.isHeadless();
        
        log.info("Initializing WebDriver for browser: {}, headless: {}", browser, headless);
        
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                log.warn("Unsupported browser '{}', defaulting to Chrome", browser);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitTimeout()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getPageLoadTimeout()));
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(config.getExplicitTimeout()));
        
        log.info("WebDriver initialized successfully");
    }
    
    public void navigateTo(String url) {
        log.info("Navigating to URL: {}", url);
        driver.get(url);
    }
    
    public void navigateToBaseUrl() {
        navigateTo(config.getUiBaseUrl());
    }
    
    public WebElement waitForElement(By locator) {
        log.debug("Waiting for element: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public WebElement waitForClickable(By locator) {
        log.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void click(By locator) {
        log.debug("Clicking on element: {}", locator);
        waitForClickable(locator).click();
    }
    
    public void type(By locator, String text) {
        log.debug("Typing '{}' into element: {}", text, locator);
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    public String getText(By locator) {
        log.debug("Getting text from element: {}", locator);
        return waitForElement(locator).getText();
    }
    
    public List<WebElement> findElements(By locator) {
        log.debug("Finding elements: {}", locator);
        return driver.findElements(locator);
    }
    
    public void close() {
        if (driver != null) {
            log.info("Closing WebDriver");
            driver.quit();
        }
    }
}
