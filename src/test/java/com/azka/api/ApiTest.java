package com.azka.api;

import com.azka.util.ConfigManager;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ApiTest {
    private static final Logger log = LogManager.getLogger(ApiTest.class);
    private ApiHelper apiHelper;
    
    @BeforeClass
    public void setup() {
        log.info("Setting up API tests");
        apiHelper = new ApiHelper();
    }
    
    @Test(groups = {"api"})
    public void testGetEndpoint() {
        log.info("Running test: testGetEndpoint");
        
        // This is a sample test. Replace with your actual endpoint
        Response response = apiHelper.get("/users/1");
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        assertNotNull(response.jsonPath().getString("id"), "User ID should not be null");
        
        log.info("Test completed successfully");
    }
}
