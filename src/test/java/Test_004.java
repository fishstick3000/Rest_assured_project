import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Test_004 {

    @BeforeClass
    public static void setup() {
        when().
                delete("http://localhost:3000/todos").
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
                post("http://localhost:3000/todos").
                then().
                statusCode(201);


    }

    @AfterClass
    public static void cleanUp() {
        when().
                delete("http://localhost:3000/todos").
                then().
                statusCode(204);
    }


}
