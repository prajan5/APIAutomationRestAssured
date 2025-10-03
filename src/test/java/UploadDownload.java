import io.restassured.response.Response;
import org.jsoup.Connection;

import java.io.File;

import static io.restassured.RestAssured.*;

public class UploadDownload {
    public void fileupload()
    {
        File file  = new File("./resources/fileupload.txt");
        Response res = given()
                .given()
                .multiPart("file",file)
                .when()
                .post("https://example.com/upload");
                System.out.println(res.getStatusCode());


    }
}
