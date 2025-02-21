package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import com.apiautomation.model.ResponseItem;
import com.apiautomation.model.request.RequestItem;
import resources.DataRequest;

import java.util.Map;

public class StepDefinitions {

    private Response response;
    private String itemId;
    private DataRequest dataRequest = new DataRequest();
    private ResponseItem responseItem;
    private RequestItem requestItem;

    @Given("A list of item are available")
    public void get_all_items() {
        // Set base URI
        RestAssured.baseURI = "https://api.restful-api.dev";

        // Send GET request to /objects
        response = RestAssured.given()
                .get("/objects");

        // Verify status code
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @When("I add item to list {string}")
    public void add_item(String payloadKey) {
        // Get payload from DataRequest
        Map<String, String> payloads = dataRequest.getPayloads();
        String payload = payloads.get(payloadKey);

        // Send POST request to /objects
        response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .post("/objects");

        // Get the ID of the created item
        itemId = response.jsonPath().getString("id");

        // Parse response to ResponseItem object
        responseItem = response.as(ResponseItem.class);
    }

    @Then("The item is available")
    public void verify_item() {
        // Send GET request to /objects/{id}
        Response getResponse = RestAssured.given()
                .pathParam("id", itemId)
                .get("/objects/{id}");

        // Parse response to ResponseItem object
        ResponseItem actualItem = getResponse.as(ResponseItem.class);

        // Verify status code
        Assert.assertEquals(getResponse.getStatusCode(), 200);

        // Verify ID
        Assert.assertEquals(actualItem.id, itemId);

        // Verify name
        Assert.assertEquals(actualItem.name, responseItem.name);

        // Verify data (optional, sesuaikan dengan kebutuhan)
        if (actualItem.data != null) {
            Assert.assertEquals(actualItem.data.year, responseItem.data.year);
            Assert.assertEquals(actualItem.data.price, responseItem.data.price, 0.001); // Delta untuk perbandingan double
            Assert.assertEquals(actualItem.data.CPUModel, responseItem.data.CPUModel);
            Assert.assertEquals(actualItem.data.hardDiskSize, responseItem.data.hardDiskSize);
        }
    }
}