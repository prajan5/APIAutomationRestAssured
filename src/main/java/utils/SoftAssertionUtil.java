package utils;
import org.testng.asserts.SoftAssert;
//creating wrapper class for the TestNG softAssert class
public class SoftAssertionUtil {


        private SoftAssert softAssert;

        public SoftAssertionUtil() {
            softAssert = new SoftAssert();

        }

        public void assertTrue(boolean condition, String message) {
            try {
                softAssert.assertTrue(condition, message);
            } catch (AssertionError e) {
                softAssert.fail(message);
            }
        }

    public void assertFalse(boolean condition, String message) {
        try {
            softAssert.assertFalse(condition, message);
        } catch (AssertionError e) {
            softAssert.fail(message);
        }
    }

        public void assertEquals(Object actual, Object expected, String message) {
            try {
                softAssert.assertEquals(actual, expected, message);
            } catch (AssertionError e) {
                softAssert.fail(message);
            }
        }

    public void assertNotEquals(Object actual, Object expected, String message) {
        try {
            softAssert.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            softAssert.fail(message);
        }
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}



