package com.azka.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UiTest {
    private static final Logger log = LogManager.getLogger(UiTest.class);
    private UiHelper uiHelper;
    
    @BeforeClass
    public void setup() {
        log.info("Setting up UI tests");
        uiHelper = new UiHelper();
    }
    
    @Test(groups = {"ui"})
    public void testBasicNavigation() {
        log.info("Running test: testBasicNavigation");
        
        // Replace with real test logic
        uiHelper.navigateToBaseUrl();
        
        // This is just a placeholder. Replace with actual locators and assertions
        String pageTitle = "Example Domain";
        assertTrue(uiHelper.waitForElement(By.tagName("h1")).isDisplayed(), "Header should be displayed");
        assertEquals(uiHelper.getText(By.tagName("h1")), pageTitle, "Page title should match expected");
        
        log.info("Test completed successfully");
    }
    
    @AfterClass
    public void tearDown() {
        log.info("Tearing down UI tests");
        if (uiHelper != null) {
            uiHelper.close();
        }
    }
}
