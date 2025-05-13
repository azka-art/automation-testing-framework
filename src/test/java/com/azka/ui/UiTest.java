package com.azka.ui;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("UI Tests")
@Feature("Example Domain Website")
public class UiTest {
    private static final Logger log = LogManager.getLogger(UiTest.class);
    private WebDriver driver;
    
    @BeforeMethod(groups = {"ui"})
    public void setup() {
        log.info("Setting up UI tests");
        
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        String headless = System.getProperty("headless", "false");
        if (headless.equals("true")) {
            log.info("Running in headless mode");
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
        driver = new ChromeDriver(options);
    }
    
    @Story("Homepage")
    @Description("Test navigasi ke Example Domain dan verifikasi title")
    @Test(groups = {"ui"})
    public void testNavigationToExampleDomain() {
        log.info("Running test: testNavigationToExampleDomain");
        
        driver.get("https://example.org/");
        
        String pageTitle = "Example Domain";
        assertTrue(driver.findElement(By.tagName("h1")).isDisplayed(), "Header should be displayed");
        assertEquals(driver.findElement(By.tagName("h1")).getText(), pageTitle, "Page title should match expected");
        
        log.info("Test completed successfully");
    }
    
    @Story("Links") 
    @Description("Test verifikasi links pada halaman Example Domain")
    @Test(groups = {"ui"})
    public void testExampleLinks() {
        log.info("Running test: testExampleLinks");
        
        driver.get("https://example.org/");
        
        assertTrue(driver.findElement(By.linkText("More information...")).isDisplayed(), 
                  "More information link should be displayed");
        
        log.info("Test completed successfully");
    }
    
    @Story("Page Content")
    @Description("Test verifikasi content pada halaman Example Domain")
    @Test(groups = {"ui"})
    public void testPageContent() {
        log.info("Running test: testPageContent");
        
        driver.get("https://example.org/");
        
        assertTrue(driver.findElement(By.tagName("p")).isDisplayed(), "Paragraph should be displayed");
        String paragraphText = driver.findElement(By.tagName("p")).getText();
        assertTrue(paragraphText.contains("This domain is for use in illustrative"), 
                  "Paragraph should contain expected text");
        
        log.info("Test completed successfully");
    }
    
    @Story("Page Structure")
    @Description("Test verifikasi struktur halaman Example Domain")
    @Test(groups = {"ui"})
    public void testPageStructure() {
        log.info("Running test: testPageStructure");
        
        driver.get("https://example.org/");
        
        // Test existence of main elements
        assertTrue(driver.findElement(By.tagName("html")).isDisplayed(), "HTML element should exist");
        assertTrue(driver.findElement(By.tagName("body")).isDisplayed(), "Body element should exist");
        assertTrue(driver.findElement(By.tagName("div")).isDisplayed(), "Div element should exist");
        
        // Test page title
        assertEquals(driver.getTitle(), "Example Domain", "Page title should match");
        
        log.info("Test completed successfully");
    }
    
    @AfterMethod(groups = {"ui"})
    public void tearDown() {
        log.info("Tearing down UI tests");
        if (driver != null) {
            driver.quit();
        }
    }
}
