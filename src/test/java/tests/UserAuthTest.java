package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import lib.ApiCoreRequests;
import lib.BaseTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lib.Assertions;

import org.junit.jupiter.api.DisplayName;

@Epic("Autorisation cases")
@Feature("Autorisation")
public class UserAuthTest extends BaseTestCase {
    private final ApiCoreRequests apiCoreRequests = new ApiCoreRequests();
    String cookie;
    String header;
    int userIDOnAuth;

    @BeforeEach
    public void loginUser(){
        Response responseGetAuth = apiCoreRequests.loginUser("vinkotov@example.com", "1234");

        this.cookie = this.getCookie(responseGetAuth, "auth_sid");
        this.header = this.getHeader(responseGetAuth, "x-csrf-token");
        this.userIDOnAuth = this.getIntFromJson(responseGetAuth, "user_id");
    }

    @Test
    @Description("This test successefully autorize user by email and password")
    @DisplayName("Test positive auth user")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("UserAuth-01")
    @Owner ("ivanov")
    public void testAuthUser() {
        Response test = apiCoreRequests.makeGetAuthRequest(this.header, this.cookie);

        Assertions.asserJsonByName(test, "user_id", this.userIDOnAuth);
    }
    @Description("This test autorisation status w/o sending auth cookie or token")
    @DisplayName("Test negative auth user")
    @ParameterizedTest
    @ValueSource(strings = {"cookie", "headers"})
    public void testNegativeAuthUser(String condition){
        Response responseForCheck;

        if (condition.equals("cookie")) {
            responseForCheck = apiCoreRequests.makeGetAuthRequestWithCookieOnly(this.cookie);
        } else if (condition.equals("headers")) {
            responseForCheck = apiCoreRequests.makeGetAuthRequestWithHeaderOnly(this.header);
        } else {
            throw new IllegalArgumentException("Condition value is known" + condition);
        }

        Assertions.asserJsonByName(responseForCheck, "user_id", 0);
    }

}
