import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Home6 {

    @Test
    public void TestRestAssured() {
        Map<String, String> headers = new HashMap<>();


        Response response= RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();
        response.prettyPrint();
        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);

    }
}
