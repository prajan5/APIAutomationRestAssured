package Postman;

import core.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.JSONReader;
import utils.PropertyReader;

import java.io.IOException;

public class PostManAuthorization extends BaseTest {

    @Test(groups="SmokeSuite")
    public void basicAuthentication()
    {   //log the res
        // first parameter will be the name and" +
        //second parameter will have the description about the test case
        ExtentReport.extentlog = ExtentReport.extentreport.
                startTest("basicAuthentication", "Validate 201 status code for Post Method");


        Response resp = given()
                .auth()
                .basic("postman","password")//Type of authorization is Basic
                .when()
                .get("https://postman-echo.com/basic-auth");

        int statuscode = resp.getStatusCode();
        assertEquals(statuscode,201);
        boolean isAuthenticated = resp.jsonPath().getBoolean("authenticated");
        assertTrue(isAuthenticated);
        System.out.println(resp.asString());

    }

    @Test(groups={"SmokeSuite","RegressionSuite"})
    public void digestAuthentication()
    {
        ExtentReport.extentlog = ExtentReport.extentreport.
                startTest("digestAuthentication", "Validate 201 status code for Post Method");
                 given()
                .auth()
                .digest("postman","password")//Type of authorization is Basic
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true));


    }

    @Test
    public void ValidateWithTestData_configurationFromJSON() throws IOException, ParseException {
        ExtentReport.extentlog = ExtentReport.extentreport.
                startTest("ValidateWithTestData_configurationFromJSON", "Validate 201 status code for Post Method");

        //Authorization from PostmanTestData.json
        String username = JSONReader.getTestData("username");
        String password = JSONReader.getTestData("password");
        String endpoint = JSONReader.getTestData("endpoint");
        //server address from properties file
        String serverAddress = PropertyReader.propertyReader("./config.properties","serverPostman");
        //Concatenate ServerAddress and Endpoint
        String URL = serverAddress+endpoint;

        System.out.println(serverAddress);
        System.out.println(username+":"+password);

        given()
                .auth()
                .digest(username,password)//Type of authorization is Basic
                .when()
                .get(URL)
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true));

        System.out.println("ValidateWithTestData_configurationFromJSON executed successfully");


    }


}
