package apiengine;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
    private RequestSpecification requestSpecification;

    public Endpoints() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        requestSpecification = RestAssured.given();
    }

    public Response getAllItems() {
        return requestSpecification
                .log()
                .all()
                .when()
                .get("/objects");
    }

    public Response addItem(String payload) {
        return requestSpecification
                .log()
                .all()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/objects");
    }

    public Response getItemById(String itemId) {
        return requestSpecification
                .log()
                .all()
                .pathParam("id", itemId)
                .when()
                .get("/objects/{id}");
    }

    public Response updateItem(String itemId, String payload) {
        return requestSpecification
                .log()
                .all()
                .pathParam("id", itemId)
                .contentType("application/json")
                .body(payload)
                .when()
                .put("/objects/{id}");
    }

    public Response deleteItem(String itemId) {
        return requestSpecification
                .log()
                .all()
                .pathParam("id", itemId)
                .when()
                .delete("/objects/{id}");
    }
}