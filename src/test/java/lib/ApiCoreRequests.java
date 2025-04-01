package lib;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class ApiCoreRequests {
    private static final String BASE_URL = "https://playground.learnqa.ru/api/user/";

    @Step("Make POST request to create user")
    public Response makePostRequest(Map<String, String> data) {
        return RestAssured
                .given()
                .body(data)
                .post(BASE_URL)
                .andReturn();
    }
// Создание пользователя с email
    @Step("Create user with email: {email}")
    public Response createUserWithEmail(String email) {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        userData.put("email", email);
        return makePostRequest(userData);
    }
// Создание пользователя без указания одного из полей
    @Step("Create user without field: '{field}'")
    public Response createUserWithoutField(String field) {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        userData.remove(field);
        return makePostRequest(userData);
    }
    // Создание пользователя с очень коротким именем в один символ
    @Step("Create user with very short name (1 character)")
    public Response createUserWithShortName() {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        userData.put("firstName", "A");
        return makePostRequest(userData);
    }
// Создание пользователя с очень длинным именем - длиннее 250 символов
    @Step("Create user with very long name (251 characters)")
    public Response createUserWithLongName() {
        Map<String, String> userData = DataGenerator.getRegistrationData();
        userData.put("firstName", new String(new char[251]).replace("\0", "A"));
        return makePostRequest(userData);
    }
}