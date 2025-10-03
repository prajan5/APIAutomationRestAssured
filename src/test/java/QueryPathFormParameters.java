import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class QueryPathFormParameters     //@Test
{
public void  validateMultiQueryParameter()
{
    baseURI="https://reqres.in/api";
    Response response =  given()
            .queryParams("page",2)
            //.queryParams("per_page",3)
            .when().get("/users")
            .then()
            .statusCode(200)
            .extract().response();
    ;

    int actualStatusCode = response.getStatusCode();
    assertEquals(actualStatusCode,200);
    //it will take integer value

}

//@Test
public void  validatePathParameter()
{
    baseURI="https://reqres.in/api";
    Response response =  given()
            //.headers("x-api-key", "reqres-free-v1")
            .pathParam("path","users")
            .queryParam("page",2)
            .when().get("/{path}");



    int actualStatusCode = response.statusCode();

    assertEquals(actualStatusCode,200);
    //print the response body in the console
    System.out.println(response.body().asString());
    //it will take integer value

}

// @Test
public void  validateformParameter()
{

    baseURI = "https://reqres.in/api";

    Response response = given()
            .log().all()
            .contentType("application/x-www-form-urlencoded") // 👈 same as Postman
            .formParam("name", "rajitha")
            .formParam("job", "testing")
            .when()
            .post("/users")
            .then()
            .log().all()
            .statusCode(201)
            .body("name", equalTo("rajitha"))
            .body("job", equalTo("testing"))
            .extract()
            .response();

    System.out.println(response.asPrettyString());


}
}
