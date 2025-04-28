package com.azka.api;

import com.azka.util.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Helper class for API testing
 */
public class ApiHelper {
    private static final Logger log = LogManager.getLogger(ApiHelper.class);
    private final RequestSpecification requestSpec;
    
    public ApiHelper() {
        ConfigManager config = ConfigManager.getInstance();
        
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(config.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + config.getApiKey())
                .build();
    }
    
    public Response get(String endpoint) {
        log.info("Executing GET request to: {}", endpoint);
        Response response = RestAssured.given(requestSpec)
                .get(endpoint);
        log.info("Response received with status code: {}", response.getStatusCode());
        return response;
    }
    
    public Response get(String endpoint, Map<String, String> queryParams) {
        log.info("Executing GET request to: {} with params: {}", endpoint, queryParams);
        Response response = RestAssured.given(requestSpec)
                .queryParams(queryParams)
                .get(endpoint);
        log.info("Response received with status code: {}", response.getStatusCode());
        return response;
    }
    
    public Response post(String endpoint, Object body) {
        log.info("Executing POST request to: {}", endpoint);
        Response response = RestAssured.given(requestSpec)
                .body(body)
                .post(endpoint);
        log.info("Response received with status code: {}", response.getStatusCode());
        return response;
    }
    
    public Response put(String endpoint, Object body) {
        log.info("Executing PUT request to: {}", endpoint);
        Response response = RestAssured.given(requestSpec)
                .body(body)
                .put(endpoint);
        log.info("Response received with status code: {}", response.getStatusCode());
        return response;
    }
    
    public Response delete(String endpoint) {
        log.info("Executing DELETE request to: {}", endpoint);
        Response response = RestAssured.given(requestSpec)
                .delete(endpoint);
        log.info("Response received with status code: {}", response.getStatusCode());
        return response;
    }
}
