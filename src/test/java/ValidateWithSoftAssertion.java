import core.StatusCode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.SoftAssertionUtil;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

@Test
public class ValidateWithSoftAssertion {
    SoftAssertionUtil softassertion = new SoftAssertionUtil();
    public void  validateGetuserWithSoftAssertion() {
        baseURI = "https://reqres.in/api";
        Response response = given()
                .queryParams("page", 2).
                when().get("/users");

        softassertion.assertEquals(response.getStatusCode(), StatusCode.NO_CONTENT, "Status code is not 200");
        softassertion.assertEquals(response.path("data[0].email"), "michael.lawson@reqres.in", "email is not correct");
        softassertion.assertAll();
    }}

