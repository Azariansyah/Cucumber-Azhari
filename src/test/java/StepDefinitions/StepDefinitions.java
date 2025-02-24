package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import com.apiautomation.model.ResponseItem;
import apiengine.Assertion;
import apiengine.Endpoints;
import Resources.DataRequest;

import java.util.Map;

public class StepDefinitions {

    private Response response;
    private String itemId;
    private DataRequest dataRequest = new DataRequest();
    private ResponseItem responseItem;
    private Endpoints endpoints = new Endpoints();
    private Assertion assertion = new Assertion();

    @Given("A list of products are available")
    public void get_all_items() {
        response = endpoints.getAllItems();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("Response: " + response.asPrettyString());
    }

    @When("I add item to list {string}")
    public void add_item(String payloadKey) {
        Map<String, String> payloads = dataRequest.getPayloads();
        String payload = payloads.get(payloadKey);

        response = endpoints.addItem(payload);
        itemId = response.jsonPath().getString("id");
        responseItem = response.as(ResponseItem.class);

        System.out.println("Add Item Response: " + response.asPrettyString());
    }

    @When("I add a specific item with name {string} and price {double}")
    public void add_specific_item(String itemName, double itemPrice) {
        String payload = "{ \"name\": \"" + itemName + "\", \"data\": { \"year\": 2023, \"price\": " + itemPrice + ", \"CPU model\": \"Snapdragon 888\", \"Hard disk size\": \"128 GB\" } }";

        response = endpoints.addItem(payload);
        itemId = response.jsonPath().getString("id");
        responseItem = response.as(ResponseItem.class);

        System.out.println("Add Specific Item Response: " + response.asPrettyString());
    }

    @Then("The item is available")
    public void verify_item() {
        Response getResponse = endpoints.getItemById(itemId);
        ResponseItem actualItem = getResponse.as(ResponseItem.class);

        Assert.assertEquals(getResponse.getStatusCode(), 200);
        assertion.assertItem(actualItem, itemId, responseItem.name);

        if (actualItem.data != null) {
            assertion.assertItemData(actualItem, responseItem.data.year, responseItem.data.price, responseItem.data.CPUModel, responseItem.data.hardDiskSize);
        }

        System.out.println("Get Item Response: " + getResponse.asPrettyString());
    }

    @Then("The item with name {string} should be available")
    public void verify_specific_item(String expectedName) {
        Response getResponse = endpoints.getItemById(itemId);
        ResponseItem actualItem = getResponse.as(ResponseItem.class);

        Assert.assertEquals(getResponse.getStatusCode(), 200);
        Assert.assertEquals(actualItem.name, expectedName);

        System.out.println("Get Specific Item Response: " + getResponse.asPrettyString());
    }
}