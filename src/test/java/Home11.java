import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Home11 {

    @Test
    public void TestCookie() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        String cookieValue = response.getCookie("HomeWork");

        assertEquals("hw_value", cookieValue, "Значение cookie 'HomeWork' не соответствует ожидаемому");
    }
}