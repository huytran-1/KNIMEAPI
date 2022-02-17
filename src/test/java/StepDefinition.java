import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;

public class StepDefinition extends Steps {
    static JSONObject apiURL;
    static JSONObject jsonHeader;
    static JSONObject jsonBody;

    @Given("I load all necessary data")
    public void i_load_all_necessary_data() throws IOException {
        this.apiURL = getJsonApiURL();
        this.jsonHeader = getJsonHeader();
        this.jsonBody = getJsonBody();
    }
    @Given("I check the spaceDetails")
    public void i_check_the_space_details() {
        sendRequest("get", apiURL.getString("details"));
        JSONObject response = getReponseJson();
        Assertions.assertTrue(response.getJSONArray("children").isEmpty());
    }
    @Then("I create new space")
    public void i_create_new_space() {
        Header header = new Header("Authorization", "Bearer " + jsonHeader.getString("Auth"));
        String url = apiURL.getString("create");
        String body = jsonBody.getString("create");
        sendRequest("put", url, header, body);
        Assertions.assertEquals(201, getResponseCode());
    }
    @Then("I verify new space is created")
    public void i_verify_new_space_is_created() {
        sendRequest("get", apiURL.getString("details"));
        JSONObject response = getReponseJson();
        Assertions.assertFalse(response.getJSONArray("children").isEmpty());
    }
    @Then("I delete new space")
    public void i_delete_new_space() {
        Header header = new Header("Authorization", "Bearer " + jsonHeader.getString("Auth"));
        String url = apiURL.getString("delete");
        sendRequest("delete", url, header);
        Assertions.assertEquals(204, getResponseCode());
    }
    @Then("I verify new space is deleted")
    public void i_verify_new_space_is_deleted() {
        sendRequest("get", apiURL.getString("details"));
        JSONObject response = getReponseJson();
        Assertions.assertTrue(response.getJSONArray("children").isEmpty());
    }

}
