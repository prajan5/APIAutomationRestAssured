package differentWaystoInput;

import core.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.PojoStudentWithList;
import pojo.PojoStudentWithListObject;
import utils.PropertyReader;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StudentsWithBooksByPOJO {

    String serverAddress = PropertyReader.propertyReader("./config.properties", "serverStudent");
    String id;

    @Test
    public void testPostStudentWithPojoListObject() {
        //Serialization converting POJO to JSON
        // Create object for PojoStudentWithLists class
        PojoStudentWithList stupojo1 = new PojoStudentWithList();// java object
        //stupojo1.setId("456");
        stupojo1.setName("Ramya");
        stupojo1.setLocation("jack");
        stupojo1.setPhone("34567897");
        //add Course by creating List
        List<String> lsStCourse = new ArrayList<>();
        lsStCourse.add("BusinessAnalytics");
        lsStCourse.add("MachineLearning");
        stupojo1.setCourses(lsStCourse);
        //Add Books. Create object for PojoStudentBooks class
        PojoStudentWithListObject books1 = new PojoStudentWithListObject();
        books1.setTitle("Maths");
        books1.setPrice(600);
        PojoStudentWithListObject books2 = new PojoStudentWithListObject();
        books2.setTitle("Physics");
        books2.setPrice(500);

        //Add books objects to the list created in the main class
        List<PojoStudentWithListObject> lsStBooks = new ArrayList<>();
        lsStBooks.add(books1);
        lsStBooks.add(books2);
        //Add List of Books to
        stupojo1.setBooks(lsStBooks);

        //Send Post request
        Response addStudentResponse = given()
                .contentType("application/json")
                .body(stupojo1)
                .when()
                .post(serverAddress);

        //Validate the status code
        assertEquals(addStudentResponse.getStatusCode(), StatusCode.CREATED.code, "status code is not 201");
        // System.out.println(addStudentResponse.getBody().asString());

        //deserialization
        PojoStudentWithList resStudentDetails = addStudentResponse.as(PojoStudentWithList.class);
        assertEquals(resStudentDetails.getCourses(), lsStCourse);
        //get Books details and validate
        List<PojoStudentWithListObject> resBooks = resStudentDetails.getBooks();
        System.out.println(resBooks);

        // size check
        Assert.assertEquals(resBooks.size(), lsStBooks.size(), "Number of books mismatch!");
        // Compare each book detaisl
        for (int i = 0; i < lsStBooks.size(); i++) {
            PojoStudentWithListObject reqBook = lsStBooks.get(i);
            PojoStudentWithListObject resBook = resBooks.get(i);

            assertEquals(resBook.getTitle(), reqBook.getTitle(), "Title mismatch at index " + i);
            assertEquals(resBook.getPrice(), reqBook.getPrice(), "Price mismatch at index " + i);
        }

    /*for (String course : resStudentDetails.getCourses()) {
     System.out.println("Course: " + course);
     }*/
        //From request with POJO Class
     /*for (PojoStudentWithListObject book : lsStBooks) {
         System.out.println("Book Title: " + book.getTitle());
         System.out.println("Book Price: " + book.getPrice());
     }*/
        //from response with POJO class
     /*for (PojoStudentWithListObject b : resStudentDetails.getBooks()) {
         System.out.println("Book Title: " + b.getTitle());
         System.out.println("Book Price: " + b.getPrice());
     }

*/
        //Access Specific element from request
    /* System.out.println(lsStBooks.get(0).getTitle()); // Maths
     System.out.println(lsStBooks.get(0).getPrice());*/


        //direct from response
  /*   List<Map<String, Object>> books = addStudentResponse.jsonPath().getList("books");

// Iterate
     for (Map<String, Object> book : books) {
         System.out.println("Title: " + book.get("title") + ", Price: " + book.get("price"));
     }
*/





    /* / Get first book title
     String firstBookTitle = addStudentResponse.jsonPath().getString("books[0].title");
     int firstBookPrice = addStudentResponse.jsonPath().getInt("books[0].price");

     System.out.println("Book 1 -> " + firstBookTitle + " : " + firstBookPrice);

// Validate second book
     String secondBookTitle = addStudentResponse.jsonPath().getString("books[1].title");
     int secondBookPrice = addStudentResponse.jsonPath().getInt("books[1].price");

     System.out.println("Book 2 -> " + secondBookTitle + " : " + secondBookPrice);*/


    }
}
