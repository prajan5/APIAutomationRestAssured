import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class BuilderImplementation {

    public RequestSpecification requestSpec;
    public ResponseSpecification responseSpec;
    Sting token = "39d9383102f2c793e85ac9527291aff1d3504a5a277a4ae39f5a9d333cc55733";

    @Test
    public void testgetRequest()
    {

     
        requestSpec = getRequestSpecificationBuilder(token,8164474,"application/json");
        responseSpec = getResponseSpecificationBuilder(200,"application/json");

      Response getResponse=  given()
               .spec(requestSpec)
                .when()
                .get("/{id}")
                .then()
              .spec(responseSpec)
              .extract().response();


        assertEquals(getResponse.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println(getResponse.getBody().jsonPath().getString("status"));
        System.out.println("get request successfully executed ");
    }



    //https://gorest.co.in/public/v2/users/8161455

    private RequestSpecification   getRequestSpecificationBuilder(String token,int pathParam,String contentType)
    {
        requestSpec = new RequestSpecBuilder()

                .setBaseUri( "https://gorest.co.in/public/v2/users")
                .addHeader("Authorization","Bearer "+token)
                .setContentType(contentType)
                .addPathParam("id",pathParam)
                .build();

        return requestSpec;
    }

    private ResponseSpecification   getResponseSpecificationBuilder(int statusCode,String contentType)
    {
        responseSpec = new ResponseSpecBuilder()

                .expectStatusCode(statusCode)
                .expectContentType(contentType)
                .build();

        return responseSpec;
    }
}
