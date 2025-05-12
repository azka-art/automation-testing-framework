package com.azka.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ApiTest {
    private static final Logger log = LogManager.getLogger(ApiTest.class);
    
    @BeforeClass(groups = {"api"})
    public void setup() {
        log.info("Setting up API tests");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
    
    @Test(groups = {"api"})
    public void testGetEndpoint() {
        log.info("Running test: testGetEndpoint");
        
        Response response = RestAssured.given()
                .get("/users/1");
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        assertNotNull(response.jsonPath().getString("id"), "User ID should not be null");
        assertEquals(response.jsonPath().getString("name"), "Leanne Graham", "User name should match expected");
        
        log.info("Test completed successfully");
    }
    
    @Test(groups = {"api"})
    public void testPostEndpoint() {
        log.info("Running test: testPostEndpoint");
        
        String requestBody = "{"
            + "\"title\": \"foo\","
            + "\"body\": \"bar\","
            + "\"userId\": 1"
            + "}";
        
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .post("/posts");
        
        assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        assertNotNull(response.jsonPath().getString("id"), "Post ID should not be null");
        assertEquals(response.jsonPath().getString("title"), "foo", "Post title should match expected");
        
        log.info("Test completed successfully");
    }
}
