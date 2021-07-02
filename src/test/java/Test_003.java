import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Test_003 {

    public static String siteTodos = "http://localhost:3000/todos";

    @BeforeClass
    public static void setup() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);

        JSONObject request = new JSONObject();
        JSONObject request2 = new JSONObject();

        request.put("title", "Create a test todo");
        request.put("completed", false);
        request.put("id", "101010101");

        request2.put("title", "Test Todo 2");
        request2.put("completed", true);
        request2.put("id", "202020202");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post(siteTodos).
                then().
                statusCode(201);

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request2.toJSONString()).
                when().
                post(siteTodos).
                then().
                statusCode(201);



    }

    @Test
    public void deleteByIdTest() {

        given().
                delete(siteTodos + "/101010101").
                then().
                statusCode(200);

    }

    @AfterClass
    public static void cleanUp() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);
    }

}
