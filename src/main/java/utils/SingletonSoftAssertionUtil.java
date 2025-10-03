package utils;
import org.testng.asserts.SoftAssert;

//creating wrapper class for the TestNG softAssert class
public class SingletonSoftAssertionUtil {


        private static SoftAssert softAssertInstance;

        private SingletonSoftAssertionUtil()
        {

        };

        public static SoftAssert getInstance() {
            if(softAssertInstance==null){
                softAssertInstance = new SoftAssert();
            }

            return softAssertInstance;

        }

        public static void assertTrue(boolean condition, String message) {
            try {
                getInstance().assertTrue(condition, message);
            } catch (AssertionError e) {
                getInstance().fail(message);
            }
        }

    public static void  assertFalse(boolean condition, String message) {
        try {
            getInstance().assertFalse(condition, message);
        } catch (AssertionError e) {
            getInstance().fail(message);
        }
    }

        public static void assertEquals(Object actual, Object expected, String message) {
            try {
                getInstance().assertEquals(actual, expected, message);
            } catch (AssertionError e) {
                getInstance().fail(message);
            }
        }

    public void assertNotEquals(Object actual, Object expected, String message) {
        try {
            getInstance().assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            getInstance().fail(message);
        }
    }

    public static void assertAll() {
        getInstance().assertAll();
    }
}



