import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest2 {

    @Test
    public void TestRestAssured() {
        Map<String, String> params = new HashMap<>();

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        String secondMessage = response.getString("messages[1]");
        System.out.println("Текст второго сообщения: " + secondMessage);

    }
}
