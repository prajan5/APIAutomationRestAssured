package differentWaystoInput;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.PojoStudent;
import pojo.PojoStudentWithList;
import utils.PropertyReader;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StudentsByPOJO {
    String serverAddress = PropertyReader.propertyReader("./config.properties","serverStudent");
    String id;

    @Test
    void convertPOJOtoJSON() throws JsonProcessingException
    {
        PojoStudent stupojo = new PojoStudent(); // java object

        stupojo.setName("Reema");
        stupojo.setLocation("France");
        stupojo.setPhone("34567897");
        String courarr[] = {"MachineLearing","DataScience"};
        stupojo.setCourses(courarr);



        Response addStudentResponse= given()
                .contentType("application/json")
                .body(stupojo)
                .when()
                .post(serverAddress);
        //.then()
        //.log().all();
        id = addStudentResponse.jsonPath().getString("id");
        assertEquals(addStudentResponse.getStatusCode(), StatusCode.CREATED.code,"status code is not 201");
        System.out.println(addStudentResponse.getBody().asString());
    }

    @Test
    void testPostStudentwithList() throws JsonProcessingException
    {
        PojoStudentWithList stupojo1 = new PojoStudentWithList();// java object
         // java object

        stupojo1.setName("Ranjini");
        stupojo1.setLocation("France");
        stupojo1.setPhone("34567897");
        List<String> lsStCourse = new ArrayList<>();
        lsStCourse.add("DataAnalyst");
        lsStCourse.add("MachineLearning");
        stupojo1.setCourses(lsStCourse);


        Response addStudentResponse= given()
                .contentType("application/json")
                .body(stupojo1)
                .when()
                .post(serverAddress);
        //.then()
        //.log().all();
        assertEquals(addStudentResponse.getStatusCode(), StatusCode.CREATED.code,"status code is not 201");
        System.out.println(addStudentResponse.getBody().asString());
    }

    @Test
    void testPatchStudent() throws JsonProcessingException
    {
        PojoStudentWithList stupojo2 = new PojoStudentWithList();// java object
        // java object

        List<String> lsStCourse = new ArrayList<>();
        lsStCourse.add("LAMA");
        lsStCourse.add("Gemini");
        stupojo2.setCourses(lsStCourse);


        Response addStudentResponse= given()
                .contentType("application/json")
                .body(stupojo2)
                .when()
                .patch(serverAddress+"/2326");
        //.then()
        //.log().all();
        assertEquals(addStudentResponse.getStatusCode(), StatusCode.SUCCESS.code,"status code is not 201");
        System.out.println(addStudentResponse.getBody().asString());
    }

}
