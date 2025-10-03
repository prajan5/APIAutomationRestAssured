package differentWaystoInput;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utils.JSONReaderGeneric.convertJSONFileToString;


public class StringAndList {
    String serverAddress = PropertyReader.propertyReader("./config.properties","serverStudent");
    String id;
 @Test
public void testinputBodyAsString()
{

      Response addStudentResponse= given()
            .contentType("application/json")
            .body("{\n" +
                    "      \"phone\": \"34123456789\",\n" +
                    "      \"name\": \"Manikkam\",\n" +
                    "      \"Courses\": [\n" +
                    "        \"Playwright\",\n" +
                    "        \"TOSCO\"\n" +
                    "      ],\n" +
                    "      \"location\": \"India\"\n" +
                    "    }\n")
            .when()
            .post(serverAddress);
            //.then()
            //.log().all();
    assertEquals(addStudentResponse.getStatusCode(), StatusCode.CREATED.code,"status code is not 201");
    System.out.println(addStudentResponse.getBody().asString());

}


@Test
public void testPatchBodyAsString()
    {

        Response patchStudentResponse= given()
                .contentType("application/json")
                .body("{\n" +
                        "\"phone\": \"7777\"\n" +
                        "}\n")
                .when()
                .patch(serverAddress+"/1");
        //.then()
        //.log().all();
        System.out.println(patchStudentResponse.getBody().asString());
        assertEquals(patchStudentResponse.getStatusCode(), StatusCode.SUCCESS.code,"status code is not 201");


    }

    @Test
    public void testPutBodyAsString()
    {

        Response putStudentResponse= given()
                .contentType("application/json")
                .body("{\n" +
                        "      \"phone\": \"12222222\",\n" +
                        "      \"name\": \"Rangoli\",\n" +
                        "      \"Courses\": [\n" +
                        "        \"Appium\",\n" +
                        "        \"TOSCO\"\n" +
                        "      ],\n" +
                        "      \"location\": \"India\"\n" +
                        "    }\n")
                .when()
                .patch(serverAddress+"/1");
        //.then()
        //.log().all();
        System.out.println(putStudentResponse.getBody().asString());
        assertEquals(putStudentResponse.getStatusCode(), StatusCode.SUCCESS.code,"status code is not 200");


    }




@Test
public void validatePostWithJsonFile() throws IOException {
    String sName = convertJSONFileToString("Students");
    Response addStudentRes  =   given()
            .contentType("application/json")
            .body(sName)
            .when()
            .post(serverAddress);

    assertEquals(addStudentRes.getStatusCode(), StatusCode.CREATED.code,"status code is not 201");
    System.out.println(addStudentRes.getBody().asString());
    id =addStudentRes.jsonPath().getString("id");
    System.out.println(id);



}

@Test
public void validatePatchWithJsonFile() throws IOException {
    String sName = convertJSONFileToString("PatchStudent");
    System.out.println(sName);
    Response patchStudentRes  =   given()
            .contentType("application/json")
            .body(sName)
            .when()
            .patch(serverAddress+"/8656");

    assertEquals(patchStudentRes.getStatusCode(), StatusCode.SUCCESS.code,"status code is not 200");
    System.out.println(patchStudentRes.getBody().asString());


}}
