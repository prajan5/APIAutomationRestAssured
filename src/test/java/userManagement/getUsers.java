package userManagement;


import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PropertyReader;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class getUsers {

    @Test(dependsOnGroups = {"RegressionSuite","SmokeSuite"})
    public void  getUserData()
    {
        //To get the data from page 2
                 given().
                 when().get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat().  //it will take integer value
                 statusCode(200);//get the status code
    }




    @Test(groups="RegressionSuite")
    public void  validateResponseBody()
    {
        baseURI = "https://jsonplaceholder.typicode.com";
        given().
                when().get("/todos/1")
                .then()
                . statusCode(200)
                .body(not(isEmptyString()))
                .body("title",equalTo("delectus aut autem")) //2 parameters
                .body("userId",equalTo(1));

    }

    //@Test
    public void validateResponsehasItems()
    {
        //set baseURI for the API
        baseURI = "https://jsonplaceholder.typicode.com";
        Response response = given()
                .when().get("/posts")
                .then()
                .extract()
                .response();

        //use hamcrest to check if the response has specific items present in the List(response body)
        assertThat(response.jsonPath().getList("title"),hasItems("nesciunt quas odio","eum et est occaecati"));
    }


//@Test
public void validateResponsehasSize()
{
    baseURI= "https://jsonplaceholder.typicode.com";
    Response response =
            given().when().get("/comments")
            .then()
            .extract()
            .response();
    //use hamcrest to check the size
    assertThat(response.jsonPath().getList(""),hasSize(500));//488 will fail

}
//@Test
public void validateResponsespecificstring()
    {
        baseURI= "https://jsonplaceholder.typicode.com";
        Response response =
                given().when().get("/comments")
                        .then()
                        .extract()
                        .response();
        //use hamcrest to check the contains specfic items in the specific order
        assertThat(response.jsonPath().getString("[0].email"), equalTo("Eliseo@gardner.biz"));
}

    //Use this when the response is not needed later.
    //All validations in one chain.
    //Cleaner and more readable.
    //.extract().response() → Used to pull the full response object out of the validation chain.
// Use it when you need to store values or re-use response later.
// If you’re only validating, do all checks inline with .then().body().
//If you need the response object, extract it once, then validate with assertEquals or response.path().
//Inline validation is cleaner when only asserting.
// Extract + Validate is needed when we reuse response values across tests or business logic.
    //@Test
    public void onlyAssertions()
    {
        baseURI = "https://reqres.in/api";

        given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                //.body("data[0].id", is(7))
                .body("data[0].email", equalTo("michael.lawson@reqres.in"))
                .body("data[0].first_name", equalTo("Michael"))
                .body("data[0].last_name", equalTo("Lawson"))
                .body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));


    }

    @Test
    public void validateUsingResponseobject()
    {
        baseURI = "https://reqres.in/api";

        Response response =
                given()
                        .queryParam("page", 2)
                        .when()
                        .get("/users")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        //  Validate using response object using TestNG valdiation
        assertEquals(response.getStatusCode(), 200);
       // assertEquals((int) response.path("data[0].id"), 7);
        assertEquals(response.path("data[0].email"), "michael.lawson@reqres.in");
        assertEquals(response.path("data[0].first_name"), "Michael");
        assertEquals(response.path("data[0].last_name"), "Lawson");
        //assertEquals(response.path("data[0].avatar"), "https://reqres.in/img/faces/7-image.jpg");

        // Example: reuse data later
        String email = response.path("data[0].email");
        System.out.println("Extracted Email: " + email);

    }







//@Test
    public void  validateAlltheFieldsFirstObject()
    {
        baseURI="https://reqres.in/api";
       Response response =  given()
               .queryParams("page",2).
                when().get("/users")
                .then()
               .statusCode(200)
               .body("data[0].id",is(7))

               .extract()
               .response() ;
        int actualStatusCode = response.getStatusCode();
        assertEquals(actualStatusCode,200);
       //response.then().body("data[0].id",is(7));
       response.then().body("data[0].email",equalTo("michael.lawson@reqres.in"));
       response.then().body("data[0].first_name",equalTo("Michael"));
        response.then().body("data[0].last_name",equalTo("Lawson"));
        response.then().body("data[0].avatar",is("https://reqres.in/img/faces/7-image.jpg"));

        //it will take integer value

    }

    @Test
    public void  validateDateFromPropertyFile()
    {
        String serverAddress = PropertyReader.propertyReader("./config.properties","serverRegressin");
        System.out.println(serverAddress);

           //baseURI = serverAddress;

                given()
                        .header("x-api-key","reqres-free-v1")
                        .queryParam("page", 2)
                        .when()
                       // .get("https://reqres.in/api/users/?page=2");
                        .get(serverAddress)
                        .then()
                        .statusCode(200)
                        .log().all();



}
 public void hardAssertion()
 {
     System.out.println("Hard assertion");
     Assert.assertTrue(true);
     Assert.assertTrue(false);
     Assert.assertTrue(true);

 }

    @Test
    public void softAssertion()
    {
        System.out.println("Soft assertion");
        SoftAssert softassertion = new SoftAssert();
        softassertion.assertTrue(true);
        softassertion.assertTrue(false);
        softassertion.assertTrue(true);
        softassertion.assertAll();


    }

}

