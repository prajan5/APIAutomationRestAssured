package StudentManagement;

import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JSONReaderGeneric;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

//import utils.JSONReader;

public class Students {
    String testDataFile = "Students";
    @Test(description="testAddStudent")
    public void testAddStudent() throws IOException, ParseException {
        //get the serverName
        String serverAddress = PropertyReader.propertyReader("./config.properties","serverStudent");


        JSONObject jsonObject = JSONReaderGeneric.getJsonData(testDataFile);
             Response addStudentRes  =   given()
                         .contentType("application/json")
                         .body(jsonObject.toString())
                .when()
                         .post(serverAddress);


       //Get the data from Json file(Requestbody)
        String sName = JSONReaderGeneric.getTestData(testDataFile,"name");
        String sLocation = JSONReaderGeneric.getTestData(testDataFile,"location");
        String sPhone = JSONReaderGeneric.getTestData(testDataFile,"phone");
        JSONArray arrCourses  = JSONReaderGeneric.getJsonArray(testDataFile,"Courses");
        Object obj  = JSONReaderGeneric.getJsonArrayData(testDataFile,"Courses",0);

        //Compare the data from Response
        SoftAssertionUtil softassertion = new SoftAssertionUtil();
       //softassertion.assertEquals(addStudentRes.getStatusCode(), StatusCode.BAD_REQUEST.code,"status code is not 201");
        softassertion.assertEquals(addStudentRes.getStatusCode(), StatusCode.CREATED.code,"status code is not 201");
        softassertion.assertEquals(addStudentRes.jsonPath().getString("name"),sName,"Name is not correct");
        softassertion.assertAll();
      //Compare individual value from array object
      assertEquals(addStudentRes.jsonPath().getList("Courses"),arrCourses);
      assertEquals(addStudentRes.jsonPath().getList("Courses").get(0),obj.toString());





    }

}
