import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Test_001 {

    @BeforeClass
    public static void setup() {
        when().
                delete("http://localhost:3000/todos").
                then().
                statusCode(204);
    }


    @Test
    public void getTodosTest() {

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

        given().
                get("http://localhost:3000/todos").
                then().
                statusCode(200).
                body("title[0]", equalTo("Create a test todo")).
                body("completed[0]", equalTo(false)).
                body("id[0]", equalTo("101010101"));

    }

    @AfterClass
    public static void cleanUp() {
        when().
                delete("http://localhost:3000/todos").
                then().
                statusCode(204);
    }

}
