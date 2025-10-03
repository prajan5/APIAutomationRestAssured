import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidateSchema {

    @Test
public void testSchemaStudentWithBooks()
{
    File schema = new File("./resources/Testdata/WrongSchemaValidation.json");
    given()
            .when()
            .get("http://localhost:3000/students")
            .then()
            .assertThat()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchema(schema));
}
}
