package AutomationTest.mission.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import AutomationTest.mission.config.ConfigReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqresClient {

    private final RequestSpecification requestSpecification;

    public ReqresClient() {
        RestAssured.useRelaxedHTTPSValidation();

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getRequired("reqres.base.url"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (ConfigReader.hasValue("reqres.api.key")) {
            builder.addHeader("x-api-key", ConfigReader.get("reqres.api.key"));
        }

        requestSpecification = builder.build();
    }

    public Response listUsers(int page) {
        return request()
                .queryParam("page", page)
                .get("/api/users");
    }

    public Response delayedUsers() {
        return request()
                .queryParam("delay", 3)
                .get("/api/users");
    }

    public Response singleUser(String userId) {
        return request()
                .get("/api/users/" + userId);
    }

    public Response createUser(String name, String job) {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("name", name);
        payload.put("job", job);

        return request()
                .body(payload)
                .post("/api/users");
    }

    public Response login(String email, String password) {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("email", email);

        if (password != null && !password.trim().isEmpty()) {
            payload.put("password", password);
        }

        return request()
                .body(payload)
                .post("/api/login");
    }

    private RequestSpecification request() {
        return given().spec(requestSpecification);
    }
}
