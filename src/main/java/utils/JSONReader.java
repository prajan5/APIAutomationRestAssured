package utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class JSONReader {


    public static String getTestData(String key) throws IOException, ParseException {
        String testDataValue;
        //jSONobjecct provides method get()
        return testDataValue= (String) getJsonData().get(key);//get the JSON object and pass the key

    }

    //JSON->JSONObject
    public static JSONObject getJsonData() throws  IOException, ParseException {

        //File is File Reader. pass the path of the testdata.json file

        File filename = new File("./resources/TestData/PostmanTestData.json");
        //convert json file into string
        //cannot convert file direct to object so convert it to string first
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into java object
        Object obj = new JSONParser().parse(json);
        //give jsonobject o that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;

    }

    public static JSONArray getJsonArray(String key) throws IOException, ParseException {
              JSONObject jsonObject = getJsonData();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;
    }

    public static Object getJsonArrayData(String key, int index) throws IOException, ParseException {
       JSONArray jsonArray = getJsonArray(key);
       return jsonArray.get(index);

    }


}
