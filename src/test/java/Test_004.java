import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Test_004 {

    public static String siteTodos = "http://localhost:3000/todos";

    @BeforeClass
    public static void setup() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);
    }

    @Test
    public void postTodoTest() {

        JSONObject request = new JSONObject();

        request.put("title", "Create a test todo");
        request.put("completed", false);
        request.put("id", "101010101");

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
                get(siteTodos).
                then().
                statusCode(200).
                body("title[0]", equalTo("Create a test todo")).
                body("completed[0]", equalTo(false)).
                body("id[0]", equalTo("101010101"));


    }

    // edge cases for duplicate fields
    // break out variables and make things more generic


    @AfterClass
    public static void cleanUp() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);
    }


}
