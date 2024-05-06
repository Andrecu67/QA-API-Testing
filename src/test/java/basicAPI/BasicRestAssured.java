package basicAPI;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssured {

    /*
        given() --> configuration > header / params / body / Auth
        when() --> method (put/post/get/delete) > url
        then() --> response -> body / code / msg / headers

        *********un test sin verificación no es un test*****
     */

    @Test
    public void createProjectByApi(){
        given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .body("{\n" +
                        "\"Content\":\"Andrea RESTASSURED\", \n" +
                        "\"Icon\":2\n" +
                        "}\n")
                .log()
                .all().
        when()
                .post("https://todo.ly/api/projects.json").
        then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo("Andrea RESTASSURED"))
                .body("Icon",equalTo(2));
    }

    @Test
    public void createProjectByApiWhitJsonObject(){
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content", "AndreaJSON");
        bodyProject.put("Icon", 2);

        given()
                .auth()
                .preemptive()
                .basic("upbapi1@upbapi.com","12345")
                .body(bodyProject.toString())
                .log()
                .all().
        when()
                .post("https://todo.ly/api/projects.json").
        then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(bodyProject.get("Icon")));
    }
}
