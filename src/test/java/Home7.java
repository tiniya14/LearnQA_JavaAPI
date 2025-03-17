import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Home7 {

    @Test
    public void TestRestAssured() {
        String url = "https://playground.learnqa.ru/api/long_redirect";
        int redirectCount = 0;
        while (true) {

            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();

            redirectCount++;

            System.out.println("Текущий URL: " + url);
            System.out.println("Статус-код: " + response.getStatusCode());

            if (response.getStatusCode() == 200) {
                System.out.println("Конечный URL: " + url);
                System.out.println("Количество редиректов: " + (redirectCount - 1));
                break;
            }

            String locationHeader = response.getHeader("Location");
            if (locationHeader == null) {
                throw new RuntimeException("Заголовок Location отсутствует в ответе");
            }

            url = locationHeader;
        }
    }
}