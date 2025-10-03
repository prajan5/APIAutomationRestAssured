package dataValidation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JSONReader;
import utils.JSONReaderGeneric;

import java.io.IOException;
import java.util.Iterator;

@Test
public class ComplexDataValidation {
String testDataFile = "Complex";
    public void testArrayKeyValue() throws IOException, ParseException {
        //Get direct values from JSON file
        String sName = JSONReaderGeneric.getTestData(testDataFile,"name");
        String sLocation = JSONReaderGeneric.getTestData(testDataFile,"location");
        String sPhone = JSONReaderGeneric.getTestData(testDataFile,"phone");

        //Get Array Courses from file
        JSONArray arrCourses  = JSONReaderGeneric.getJsonArray(testDataFile,"Courses");

        //  Get the first object
       // JSONObject firstCourse = (JSONObject) arrCourses.get(0);
       // System.out.println(firstCourse.get("title"));

        System.out.println("Print Courses values from ListArray");
        for (Object obj : arrCourses) {
            JSONObject course = (JSONObject) obj;
            System.out.println("Course: " + course.get("title") + ", Fee: " + course.get("fee"));

        }


        System.out.println("All courses reterived");





        System.out.println("Print Technology values from Array");
        JSONArray arrContact  = JSONReaderGeneric.getJsonArray(testDataFile,"Technology");
        for (String s : (Iterable<String>) arrContact) {
            System.out.println(s);
        }

        System.out.println("Print Contact by index");
        Object obj  = JSONReaderGeneric.getJsonArrayData(testDataFile,"Contact",0);
        System.out.println(obj.toString());



        //JSONArray arrCourses  = JSONReaderGeneric.getJsonArray(testDataFile,"Courses");
        Object obj1  = JSONReaderGeneric.getJsonArrayData(testDataFile,"Courses",1);
        // System.out.println(obj1.toString());
        JSONObject objJSON = (JSONObject) obj1;
        System.out.println( objJSON.get("title"));

        //JSONReader.getTestData("title");
        //JSONObject jsonObject = JSONReaderGeneric.getJsonData(testDataFile);
        //Object obj  = JSONReaderGeneric.getJsonArrayData(testDataFile,"Courses",0);

        //if other dependcy used org.json.jsonarry use this
        /*for (int i = 0; i < arrContact.length(); i++) {
            String s = arrContact.getString(i);
            System.out.println(s);
        }*/
    }
}
