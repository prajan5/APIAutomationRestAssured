package userManagement;

import com.github.javafaker.Faker;
import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class CRUDOperations {
 int id;
    //Generated using GitHub in https://gorest.co.in/
    String token = "39d9383102f2c793e85ac9527291aff1d3504a5a277a4ae39f5a9d333cc55733";

    @Test(priority=1)
    public void createUser() {
        //Add headers in MAP
        Map<String, String> mheaders = new HashMap<>();
        mheaders.put("Authorization", "Bearer " +token);
        mheaders.put("Content-Type", "application/json");
        //request body


        Faker faker = new Faker();
        HashMap data = new HashMap();
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        Response postResponse = given()
                .headers(mheaders)
                .body(data)
                .when()
                .post("https://gorest.co.in/public/v2/users")
               .then()
                .log().all() // logs everything (most commonly used).

                .log().body() //→ logs only response body.

                //.log().headers() //→ logs only headers.

                //.log().status() //→ logs only status line.

                //.log().ifError() //→ logs only if the request fails.
                //.statusCode(201)
               .extract().response();

        //call enum class for status code
        assertEquals(postResponse.getStatusCode(), StatusCode.CREATED.code);

        //get the id created for POST request
         id = postResponse.jsonPath().getInt("id");
        System.out.println(id);
        System.out.println("Create request successfully executed ");

    }

    @Test(priority=2)
    public void testgetRequest()
    {
        Response  getResponse = given()
                .header("Authorization","Bearer "+token)
                .pathParam("userId",id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{userId}");
               // .then()
                //.statusCode(200)
                //.log().all();

        assertEquals(getResponse.getStatusCode(),StatusCode.SUCCESS.code);
        System.out.println("get request successfully executed ");
    }

    @Test(priority=3)
    public void testdeleteRequest()
    {
       Response  deleteResponse =  given()
                .header("Authorization","Bearer "+token)
                .pathParam("userId",id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{userId}");
                //.then()
                //.statusCode(204)
               // .log().all();

        assertEquals(deleteResponse.getStatusCode(),StatusCode.NO_CONTENT.code);
        System.out.println("Delete request successfully executed ");
    }


}
