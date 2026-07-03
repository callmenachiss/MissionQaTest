package AutomationTest.mission.api;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ReqresAssertions {

    private ReqresAssertions() {
    }

    public static void statusCodeIs(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected API response status code.");
    }

    public static void totalUsersMatchesCollectedIds(int totalUsers, List<Integer> userIds) {
        Assert.assertEquals(userIds.size(), totalUsers, "Collected user id count should match API total.");
    }

    public static void userDataMatches(Response response, Map<String, String> expectedUserData) {
        statusCodeIs(response, 200);
        for (Map.Entry<String, String> expected : expectedUserData.entrySet()) {
            Assert.assertEquals(
                    response.jsonPath().getString("data." + expected.getKey()),
                    expected.getValue(),
                    "Unexpected value for user field: " + expected.getKey()
            );
        }
    }

    public static void createdUserMatches(Response response, String expectedName, String expectedJob) {
        statusCodeIs(response, 201);
        Assert.assertEquals(response.jsonPath().getString("name"), expectedName, "Created user name should match.");
        Assert.assertEquals(response.jsonPath().getString("job"), expectedJob, "Created user job should match.");
        Assert.assertNotNull(response.jsonPath().getString("id"), "Created user id should be present.");
        Assert.assertNotNull(response.jsonPath().getString("createdAt"), "Created timestamp should be present.");
    }

    public static void usersHaveUniqueIds(Response response) {
        statusCodeIs(response, 200);
        List<Integer> ids = response.jsonPath().getList("data.id");
        Set<Integer> uniqueIds = new HashSet<Integer>(ids);
        Assert.assertEquals(uniqueIds.size(), ids.size(), "Every user id should be unique.");
    }

    public static void errorMessageIs(Response response, String expectedMessage) {
        Assert.assertEquals(response.jsonPath().getString("error"), expectedMessage, "Unexpected API error message.");
    }
}
