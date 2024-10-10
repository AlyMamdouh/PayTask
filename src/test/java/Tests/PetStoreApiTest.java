
package Tests;

import Utilities.Utility;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class PetStoreApiTest {

    private static final Logger logger = LogManager.getLogger(PetStoreApiTest.class);
    private final String baseUrl = "https://petstore.swagger.io/v2/pet";



    @Test
    public void testGetRandomPetById() {
        int petId = 1;
        logger.info("Fetching Pet with ID: {}", petId);


        Response checkResponse = given()
                .header("Accept", "*/*")
                .when()
                .get(baseUrl + "/" + petId);

        Assert.assertEquals(checkResponse.getStatusCode(), 200, "Pet not found with ID: " + petId);

        Response response = given()
                .header("Accept", "*/*")
                .when()
                .get(baseUrl + "/" + petId);


        Utility.LoggingUtils.logRequestAndResponse(response, logger);


        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but received: " + response.getStatusCode());


        Assert.assertTrue(response.getBody().asString().contains("name"), "Response does not contain 'name' field.");


        String petName = response.jsonPath().getString("name");
        logger.info("Pet Name: {}", petName);
    }



    @Test
    public void testGetAllPetsByStatus() {
        String status = "available"; // Change as needed
        logger.info("Fetching all pets with status: {}", status);

        Response response = given()
                .header("Accept", "*/*")
                .queryParam("status", status)
                .when()
                .get(baseUrl + "/findByStatus");

        // Logging the request and response
        Utility.LoggingUtils.logRequestAndResponse(response, logger);

        // Validate status code and response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but received: " + response.getStatusCode());
        logger.info("Fetched Pets: {}", response.jsonPath().getList("name"));
    }




    @Test
    public void testCreateNewPet() {
        String petPayload = "{ \"id\": 10, \"name\": \"Buddy\", \"status\": \"available\" }"; // Sample payload
        logger.info("Creating new pet with payload: {}", petPayload);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(petPayload)
                .when()
                .post(baseUrl);

        // Logging the request and response
        Utility.LoggingUtils.logRequestAndResponse(response, logger);

        // Validate status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but received: " + response.getStatusCode());
        String petName = response.jsonPath().getString("name");
        logger.info("Created Pet Name: {}", petName);
    }
}


