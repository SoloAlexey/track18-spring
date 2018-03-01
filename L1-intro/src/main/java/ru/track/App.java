package ru.track;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;


/**
 * TASK:
 * POST request to  https://guarded-mesa-31536.herokuapp.com/track
 * fields: name,github,email
 *
 * LIB: http://unirest.io/java.html
 *
 *
 */

public class App {

    public static final String URL = "http://guarded-mesa-31536.herokuapp.com/track";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_GITHUB = "github";
    public static final String FIELD_EMAIL = "email";

    public static void main(String[] args) throws Exception {
       HttpResponse<JsonNode> jsonResponse = Unirest.post(URL)
                .header("accept", "application/json")
                .field(FIELD_NAME, "Солодун Алексей ")
                .field(FIELD_GITHUB, "github.com/SoloAlexey")
                .field(FIELD_EMAIL, "solodun@phystech.edu")
                .asJson();
        System.out.println(jsonResponse.getBody().toString());
        boolean success = false;
    }

}
