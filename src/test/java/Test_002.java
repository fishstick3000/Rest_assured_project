import org.junit.Test;
import static io.restassured.RestAssured.when;

public class Test_002 {

    @Test
    public void testDeleteAll() {

        when().
                delete("http://localhost:3000/todos").
                then().
                statusCode(204);

    }

}
