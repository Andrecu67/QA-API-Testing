package basicAPI;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDProjectTest {
    @Test
    public void createUpdateReadDeleteProject(){
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content", "AndreaJSON");
        bodyProject.put("Icon", 2);

        // Create
        Response response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .body(bodyProject.toString())
                .log()
                .all().
        when()
                .post("https://todo.ly/api/projects.json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(bodyProject.get("Icon")));

        int idProject = response.then().extract().path("Id");

        // Update
        bodyProject.put("Content", "JSON update");
        response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .body(bodyProject.toString())
                .log()
                .all().
        when()
                .put("https://todo.ly/api/projects/"+ idProject+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(bodyProject.get("Icon")));

        // Read
        response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .log()
                .all().
        when()
                .get ("https://todo.ly/api/projects/"+ idProject+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(bodyProject.get("Icon")));

        //Delete
        response = given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .log()
                .all().
        when()
                .delete ("https://todo.ly/api/projects/"+ idProject+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Deleted",equalTo(true))
                .body("Icon",equalTo(bodyProject.get("Icon")));
    }
}
