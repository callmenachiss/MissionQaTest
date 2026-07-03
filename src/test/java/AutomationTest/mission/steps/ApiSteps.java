package AutomationTest.mission.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import AutomationTest.mission.api.ReqresAssertions;
import AutomationTest.mission.api.ReqresClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiSteps {

    private final ReqresClient reqresClient = new ReqresClient();
    private Response response;
    private Response delayedResponse;
    private int totalUsers;
    private int totalPages;
    private String createdUserName;
    private String createdUserJob;
    private final List<Integer> collectedUserIds = new ArrayList<>();

    private void log(String message) {
        System.out.println("[API] " + message);
    }

    @Given("^I get the default list of users for on (\\d+)(?:st|nd|rd|th) page$")
    public void getDefaultListOfUsers(int page) {
        response = reqresClient.listUsers(page);
        ReqresAssertions.statusCodeIs(response, 200);

        totalUsers = response.jsonPath().getInt("total");
        totalPages = response.jsonPath().getInt("total_pages");
        collectedUserIds.addAll(response.jsonPath().getList("data.id"));

        log("Page " + page + " fetched - total users: " + totalUsers + ", total pages: " + totalPages);
    }

    @When("^I get the list of all users within every page$")
    public void getUsersFromEveryPage() {

        for (int page = 2; page <= totalPages; page++) {

            Response pageResponse = reqresClient.listUsers(page);

            ReqresAssertions.statusCodeIs(pageResponse, 200);

            collectedUserIds.addAll(pageResponse.jsonPath().getList("data.id"));

            log("Page " + page + " fetched - user ids collected so far: " + collectedUserIds.size());
        }
    }

    @Then("^I should see total users count equals the number of user ids$")
    public void totalUsersShouldMatchCollectedIds() {

        log("Total users reported by API: " + totalUsers +
                ", user ids collected: " + collectedUserIds.size());

        ReqresAssertions.totalUsersMatchesCollectedIds(totalUsers, collectedUserIds);
    }

    @Given("^I make a search for user (.*)$")
    public void searchForUser(String userId) {

        response = reqresClient.singleUser(userId);

        log("Searched for user id " + userId +
                " - response status: " + response.statusCode());
    }

    @Then("^I should see the following user data$")
    public void userDataShouldMatch(DataTable dataTable) {

        Map<String, String> expectedUserData =
                dataTable.asMaps(String.class, String.class).get(0);

        log("User returned - first_name: "
                + response.jsonPath().getString("data.first_name")
                + ", email: "
                + response.jsonPath().getString("data.email"));

        ReqresAssertions.userDataMatches(response, expectedUserData);
    }

    @Then("^I receive error code (\\d+) in response$")
    public void responseCodeShouldBe(int responseCode) {

        log("Response status code: " + response.statusCode()
                + " (expected " + responseCode + ")");

        ReqresAssertions.statusCodeIs(response, responseCode);
    }

    @Given("^I create a user with following (.*) (.*)$")
    public void createUser(String name, String job) {

        createdUserName = name;
        createdUserJob = job;

        response = reqresClient.createUser(name, job);

        log("Create user request sent - name: "
                + name + ", job: " + job);
    }

    @Then("^response should contain the following data$")
    public void createdResponseShouldContainExpectedData(DataTable dataTable) {

        log("User created - id: "
                + response.jsonPath().getString("id")
                + ", name: "
                + response.jsonPath().getString("name")
                + ", job: "
                + response.jsonPath().getString("job")
                + ", createdAt: "
                + response.jsonPath().getString("createdAt"));

        ReqresAssertions.createdUserMatches(response, createdUserName, createdUserJob);
    }

    @Given("^I login unsuccessfully with the following data$")
    public void loginWithData(DataTable dataTable) {

        Map<String, String> credentials =
                dataTable.asMaps(String.class, String.class).get(0);

        response = reqresClient.login(
                credentials.get("Email"),
                credentials.get("Password"));

        log("Login attempted - email: "
                + credentials.get("Email"));
    }

    @Then("^I should get a response code of (\\d+)$")
    public void loginResponseCodeShouldBe(int responseCode) {

        log("Login response status code: "
                + response.statusCode()
                + " (expected " + responseCode + ")");

        ReqresAssertions.statusCodeIs(response, responseCode);
    }

    @And("^I should see the following response message:$")
    public void responseMessageShouldMatch(DataTable dataTable) {

        String expectedMessage = dataTable.asLists()
                .get(0)
                .get(0)
                .replace("\"error\":", "")
                .replace("\"", "")
                .trim();

        log("Login error message returned: "
                + response.jsonPath().getString("error"));

        ReqresAssertions.errorMessageIs(response, expectedMessage);
    }

    @Given("^I wait for the user list to load$")
    public void waitForUserListToLoad() {

        log("Requesting user list with a 3-second artificial delay...");

        delayedResponse = reqresClient.delayedUsers();

        log("Delayed response received - status: "
                + delayedResponse.statusCode());
    }

    @Then("^I should see that every user has a unique id$")
    public void everyUserShouldHaveUniqueId() {

        log("User ids returned: "
                + delayedResponse.jsonPath().getList("data.id"));

        ReqresAssertions.usersHaveUniqueIds(delayedResponse);
    }
}