package com.azka.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Epic("API Tests") 
@Feature("JSONPlaceholder API")
public class ApiTest {
    private static final Logger log = LogManager.getLogger(ApiTest.class);
    
    @BeforeClass(groups = {"api"})
    public void setup() {
        log.info("Setting up API tests");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
    
    @Story("User endpoint")
    @Description("Test GET endpoint untuk mendapatkan user berdasarkan ID")
    @Test(groups = {"api"})
    public void testGetUserById() {
        log.info("Running test: testGetUserById");
        
        Response response = RestAssured.given()
                .get("/users/1");
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        assertNotNull(response.jsonPath().getString("id"), "User ID should not be null");
        assertEquals(response.jsonPath().getString("name"), "Leanne Graham", "User name should match expected");
        
        log.info("Test completed successfully");
    }
    
    @Story("User endpoint")
    @Description("Test GET endpoint untuk mendapatkan semua users")
    @Test(groups = {"api"})
    public void testGetAllUsers() {
        log.info("Running test: testGetAllUsers");
        
        Response response = RestAssured.given()
                .get("/users");
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        assertNotNull(response.jsonPath().getList("$"), "Users list should not be null");
        assertEquals(response.jsonPath().getList("$").size(), 10, "Should return 10 users");
        
        log.info("Test completed successfully");
    }
    
    @Story("Post endpoint")
    @Description("Test POST endpoint untuk membuat post baru")
    @Test(groups = {"api"})
    public void testCreatePost() {
        log.info("Running test: testCreatePost");
        
        String requestBody = "{\"title\": \"foo\",\"body\": \"bar\",\"userId\": 1}";
        
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .post("/posts");
        
        assertEquals(response.getStatusCode(), 201, "Expected status code 201");
        assertNotNull(response.jsonPath().getString("id"), "Post ID should not be null");
        assertEquals(response.jsonPath().getString("title"), "foo", "Post title should match expected");
        
        log.info("Test completed successfully");
    }
    
    @Story("Post endpoint")
    @Description("Test PUT endpoint untuk update post")
    @Test(groups = {"api"})
    public void testUpdatePost() {
        log.info("Running test: testUpdatePost");
        
        String requestBody = "{\"id\": 1,\"title\": \"updated title\",\"body\": \"updated body\",\"userId\": 1}";
        
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .put("/posts/1");
        
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        assertEquals(response.jsonPath().getString("title"), "updated title", "Post title should be updated");
        assertEquals(response.jsonPath().getString("body"), "updated body", "Post body should be updated");
        
        log.info("Test completed successfully");
    }
}
