import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Test_002 {

    public static String siteTodos = "http://localhost:3000/todos";


    @BeforeClass
    public static void setup() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);

        JSONObject request2 = new JSONObject();
        JSONObject request3 = new JSONObject();

        request2.put("title", "Test Todo 2");
        request2.put("completed", true);
        request2.put("id", "202020202");

        request3.put("title", "Test Todo 3");
        request3.put("completed", false);
        request3.put("id", "303030303");

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request2.toJSONString()).
                when().
                post(siteTodos).
                then().
                statusCode(201);

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request3.toJSONString()).
                when().
                post(siteTodos).
                then().
                statusCode(201);

    }

    @Test
    public void testDeleteAll() {

        when().
                delete(siteTodos).
                then().
                statusCode(204);

    }

    @AfterClass
    public static void cleanUp() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);
    }

}
