package utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JSONReaderGeneric {


    public static String getTestData(String testDataFile,String key) throws IOException, ParseException {
        String testDataValue;
        //jSONobjecct provides method get()
        return testDataValue= (String) getJsonData(testDataFile).get(key);//get the JSON object and pass the key

    }

    //JSON->JSONObject
    public static JSONObject getJsonData(String testDataFile) throws  IOException, ParseException {

        //File is File Reader. pass the path of the testdata.json file

        File filename = new File("./resources/TestData/"+testDataFile+".json");
        //convert json file into string
        //cannot convert file direct to object so convert it to string first
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into java object
        Object obj = new JSONParser().parse(json);
        //convert java object  jason object
        JSONObject objJSON = (JSONObject) obj;
        return objJSON;

    }

    public static JSONArray getJsonArray(String testDataFile,String key) throws IOException, ParseException {
              JSONObject jsonObject = getJsonData(testDataFile);
        JSONArray arrayJSON = (JSONArray) jsonObject.get(key);
        return arrayJSON;
    }

    public static Object getJsonArrayData(String testDataFile,String key, int index) throws IOException, ParseException {
        JSONArray arrJSON = getJsonArray(testDataFile, key);
        return arrJSON.get(index);
    }

    public static String convertJSONFileToString(String testDataFile) throws IOException {
            String json;
        try {
            File filepath = new File(System.getProperty("user.dir") + "/resources/TestData/"+testDataFile+".json");
            FileInputStream fi = new FileInputStream(filepath);
             json = IOUtils.toString(fi);
        }
        catch (FileNotFoundException e)

        {
            throw new RuntimeException(e);
        }

        return json;

    }
}
