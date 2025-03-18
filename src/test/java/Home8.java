import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Home8 {

    @Test
    public void testLongtimeJob() throws InterruptedException {

        Response response = RestAssured
                .given()
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job");

        assertEquals(200, response.getStatusCode(), "Ошибка при создании задачи");

        JsonPath createTaskJson = response.jsonPath();
        String token = createTaskJson.getString("token");
        int seconds = createTaskJson.getInt("seconds");

        System.out.println("Token: " + token + ", Ожидание: " + seconds + " секунд");


        Response statusBeforeCompletionResponse = RestAssured
                .given()
                .queryParam("token", token)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job");

        assertEquals(200, statusBeforeCompletionResponse.getStatusCode(), "Ошибка при проверке статуса задачи");

        JsonPath statusBeforeCompletionJson = statusBeforeCompletionResponse.jsonPath();
        String statusBeforeCompletion = statusBeforeCompletionJson.getString("status");

        assertEquals("Job is NOT ready", statusBeforeCompletion, "Неверный статус задачи до завершения");
        System.out.println("Статус задачи до завершения: " + statusBeforeCompletion);


        System.out.println("Ожидание завершения задачи: " + seconds + " секунд");
        Thread.sleep(seconds * 1000L);


        Response statusAfterCompletionResponse = RestAssured
                .given()
                .queryParam("token", token)
                .when()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job");

        assertEquals(200, statusAfterCompletionResponse.getStatusCode(), "Ошибка при проверке статуса задачи");

        JsonPath statusAfterCompletionJson = statusAfterCompletionResponse.jsonPath();
        String statusAfterCompletion = statusAfterCompletionJson.getString("status");
        String result = statusAfterCompletionJson.getString("result");

        assertEquals("Job is ready", statusAfterCompletion, "Неверный статус задачи после завершения");
        assertNotNull(result, "Отсутствует результат задачи");
        System.out.println("Статус задачи после завершения: " + statusAfterCompletion);
        System.out.println("Результат задачи: " + result);
    }
}