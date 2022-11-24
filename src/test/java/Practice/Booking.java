package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Booking {
    @Test
    public static void getBooking() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-Type Json successfully and status code");
    }

    @Test
    public static void getBookingId() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-Type Json successfully and status code");

    }
    @Test
    public void useParam(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        String path = "booking";

        RestAssured.given()
                .queryParam("firstname","Nurik")
                .queryParam("lastname","Zhekebatyrov")
                .queryParam("totalprice", "111")
                .queryParam("additionalneeds", "Breakfast")
                .when()
                .post(path)
                .then()
                .assertThat()
                .contentType(ContentType.TEXT)
                .and()
                .statusCode(200)
                .and()
                .log().all();

    }


}