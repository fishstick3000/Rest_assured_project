import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Test_001 {

    public static String siteTodos = "http://localhost:3000/todos";


    @BeforeClass
    public static void setup() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);
    }


    @Test
    public void getTodosTest() {

        JSONObject request1 = new JSONObject();
        JSONObject request2 = new JSONObject();
        JSONObject request3 = new JSONObject();

        request1.put("title", "Create a test todo");
        request1.put("completed", false);
        request1.put("id", "101010101");

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
                body(request1.toJSONString()).
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

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request3.toJSONString()).
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

    //try adding more list items to verify list has more than one item

    @AfterClass
    public static void cleanUp() {
        when().
                delete(siteTodos).
                then().
                statusCode(204);
    }

}
