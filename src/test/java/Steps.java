import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Steps {
    protected Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public JSONObject getJsonApiURL() throws IOException {
        JSONObject apiURL = new JSONObject(FileUtils.readFileToString((new File("./src/test/resource/apiURL.json")), "utf-8"));
        return apiURL;
    }

    public JSONObject getJsonHeader() throws IOException {
        JSONObject jsonHeader = new JSONObject(FileUtils.readFileToString((new File("./src/test/resource/header.json")), "utf-8"));
        return jsonHeader;
    }

    public JSONObject getJsonBody() throws IOException {
        JSONObject jsonBody = new JSONObject(FileUtils.readFileToString((new File("./src/test/resource/body.json")), "utf-8"));
        return jsonBody;
    }

    public void sendRequest(String method, String url){
        Response res = null;
        switch (method){
            case "put":
                res = RestAssured.put(url);
                break;
            case "get":
                res = RestAssured.get(url);
                break;
            case "delete":
                res = RestAssured.delete(url);
                break;
            case "post":
                res = RestAssured.post(url);
                break;
        }
        setResponse(res);
    }
    public void sendRequest(String method, String url, Header header){
        Response res = null;
        switch (method){
            case "put":
                res = RestAssured.given().
                        header(header).
                        put(url);
                break;
            case "get":
                res = RestAssured.given().
                        header(header).
                        get(url);
                break;
            case "delete":
                res = RestAssured.given().
                        header(header).
                        delete(url);
                break;
            case "post":
                res = RestAssured.given().
                        header(header).
                        post(url);
                break;
        }
        setResponse(res);
    }

    public void sendRequest(String method, String url, Header header, String body){
        Response res = null;
        switch (method){
            case "put":
                res = RestAssured.given().
                        header(header).
                        body(body).
                        put(url);
                break;
            case "get":
                res = RestAssured.given().
                        header(header).
                        body(body).
                        get(url);
                break;
            case "delete":
                res = RestAssured.given().
                        header(header).
                        body(body).
                        delete(url);
                break;
            case "post":
                res = RestAssured.given().
                        header(header).
                        body(body).
                        post(url);
                break;
        }
        setResponse(res);
    }

    public JSONObject getReponseJson(){
        JSONObject responseJson = new JSONObject(getResponse().body().asString());
        return responseJson;
    }

    public int getResponseCode(){
        return getResponse().statusCode();
    }

}
