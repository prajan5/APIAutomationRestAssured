import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.isEmptyString;
import static org.testng.Assert.assertEquals;

public class HeadersCookies {

    //@Test
    public void testtheheaders() {

        baseURI = "https://reqres.in/api";

        Response response = given()
                .log().all()
                .header("Authorization", "bearer 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")
                .header("ContentType", "application/json")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asPrettyString());


    }

    //@Test
    public void testMultipleheaders() {
        Map<String, String> mheaders = new HashMap<>();
        mheaders.put("Authorization", "bearer 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");
        mheaders.put("ContentType", "application/json");


        Response response = given()
                .log().all()
                .headers(mheaders)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.getHeaders());
        System.out.println(response.statusCode);
        //System.out.println(response.asPrettyString());


    }


    @Test
    public void validateResponseHeaders() {
        //set baseURI for the API

        baseURI = "https://reqres.in/api";
        Response response = given()
                //.headers("x-api-key", "reqres-free-v1")
                .pathParam("path", "users")
                .queryParam("page", 2)
                .when().get("/{path}");

        //check if server exists
        // assertThat(headers.hasHeaderWithName("Server"), is(true));

        Headers headers = response.getHeaders();// It does not return a Map<String,String> rather it resurns object


        for (Header hd : headers) {
            if (hd.getName().contains("Server")) {
                //System.out.println(hd.getName() + ":" + hd.getValue());
                assertEquals(hd.getValue(), "cloudflare");
                System.out.println("validateResponseHeaders is executed successfully");
            }
        }
    }

    //@Test
    public void sendCookies() {
        //set baseURI for the API

        baseURI = "https://reqres.in/api";
        Response response = given()
                .cookie("cookieKey1", "valueCookie1")//set cookies
                .cookie("cookieKey2", "valueCookie2")
                .pathParam("path", "users")
                .queryParam("page", 2)
                .when().get("/{path}");

    }


    //@Test
    public void sendCookiesUsingBuillder() {

        Cookie cookies = new Cookie.Builder("cookieKey1", "valueCookie1")
                .setComment("using this cookie for store")
                .build();


        //set baseURI for the API

        baseURI = "https://reqres.in/api";
        Response response = given()
                .cookie(cookies)
                .pathParam("path", "users")
                .queryParam("page", 2)
                .when().get("/{path}")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Cookies passed successfully through builder ");

    }


    @Test
    public void fetchCookies() {
        //set baseURI for the API

        Response response = given()
                .when()
                .get("https://www.google.com/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // System.out.println(response.cookies());
        //Mehtod1
        Map<String, String> cookies = response.getCookies();
        for (Map.Entry<String, String> c : cookies.entrySet()) {
            //System.out.println(c.getKey() +":"+c.getValue());
            if (c.getKey().contains("AEC")) {
                System.out.println(c.getKey() + ":" + c.getValue());
            }
        }

        //check if Map has the key "AEC"
        assertThat(cookies, hasKey("AEC"));
        // assertThat(cookies,hasValue("AaJma5v_M476-swLvcKm2I3hZTWXPhIZb2wrC92WEuQrm4Ut5f8FzTCt4g"));//assertion error because cookie values like "AEC" are dynamic and cannot be hardcoded.

        //print the value for cookie "AEC" by getCookie
        //System.out.println(response.getCookie("AEC"));
        //Best practice → check cookie existence, non-null, or pattern, not the exact value.
        String aecValue = cookies.get("AEC");
        assertThat(aecValue, notNullValue());//it may fail because cookie
        assertThat(aecValue, not(isEmptyString()));

        //Method2
        Cookies cookies1 = response.getDetailedCookies();
        System.out.println(cookies1.getValue("NID"));


    }
}