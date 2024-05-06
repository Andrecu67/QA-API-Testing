package exerciseAPI;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItem {
    @Test
    public void createUpdateReadDeleteItem(){
        JSONObject bodyItem = new JSONObject();
        bodyItem.put("Content", "AndreaItem");
        bodyItem.put("ProjectId", 4207409);

        // Create
        Response response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .body(bodyItem.toString())
                .log()
                .all().
        when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")))
                .body("ProjectId",equalTo(bodyItem.get("ProjectId")));

        int idItem = response.then().extract().path("Id");

        // Update
        bodyItem.put("Content", "AndreaItem update");
        response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .body(bodyItem.toString())
                .log()
                .all().
        when()
                .put("https://todo.ly/api/items/"+ idItem+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")))
                .body("ProjectId",equalTo(bodyItem.get("ProjectId")));

        // Read
        response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .log()
                .all().
        when()
                .get ("https://todo.ly/api/items/"+ idItem+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")))
                .body("ProjectId",equalTo(bodyItem.get("ProjectId")));

        //Delete
        response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .log()
                .all().
        when()
                .delete ("https://todo.ly/api/items/"+ idItem+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyItem.get("Content")))
                .body("Deleted",equalTo(true))
                .body("ProjectId",equalTo(bodyItem.get("ProjectId")));
    }
}
