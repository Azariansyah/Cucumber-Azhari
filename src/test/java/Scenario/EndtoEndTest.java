package Scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseItem;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class EndtoEndTest {

    ResponseItem responseItem;

    /*
     * Scenario e2e test
     * 1. Hit add products (verify response)
     * 2. Hit get Products (verify response)
     * 3. Hit update product (verify response)
     */

    @Test
    public void ScenarioE2ETest(){
        String json = "{\n" +
                "   \"name\": \"Poco F6\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2024,\n" +
                "      \"price\": 5500000,\n" +
                "      \"CPU model\": \"Snapdragon 8s Gen 3\",\n" +
                "      \"Hard disk size\": \"512 GB\"\n" +
                "   }\n" +
                "}";
        // Add product
        RestAssured.baseURI = "https:restful-api.dev";

        Response response = RestAssured.given()
                .log()
                .all()
                .body(json)
                .contentType("application/json")
                .when()
                .post("/objects");
        System.out.println("add Object" + response.asPrettyString());
        JsonPath addJsonPath = response.jsonPath();
        responseItem = addJsonPath.getObject("", ResponseItem.class);

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(responseItem.name,"Poco F6");
        Assert.assertEquals(responseItem.data.year, 2024);
        Assert.assertEquals(responseItem.data.price, 5500000);
        Assert.assertEquals(responseItem.data.CPUModel, "Snapdragon 8s Gen 3");
        Assert.assertEquals(responseItem.data.hardDiskSize, "512 GB");

        String idObject = responseItem.id;

        //Get Product
        Response response2 = given()
                .pathParam("path", "products")
                .pathParam("idProduct", idObject)
                .log()
                .all()
                .when()
                .get("{path}/{idProduct}");
        System.out.println("response2" + response2.asPrettyString());

        //validation POJO

        //Update Product
        Response responseUpdate = given()
                .log()
                .all()
                .pathParam("path", "products")
                .pathParam("idProduct", idObject)
                .body(json)
                .contentType("application/json")
                .when()
                .put("{path}/{idProduct}");
        System.out.println("update product" + responseUpdate.asPrettyString());

        //Validation POJO

    }



    /*
     * Gherkin
     * 1. Feature
     * - Given, Then, When, And,But
     *
     *
     * - Checkout barang
     * Given :
     * - user login to apps
     *
     * When :
     * - action -> user checkout item
     *
     * Then :
     * - result/expectation scenario
     * - user successfully checkout
     *
     * And :
     * simply prefix di step
     *
     * 2. Stepdefenition
     * 3. Runner
     */


}
