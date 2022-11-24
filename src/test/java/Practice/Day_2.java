package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Day_2 {
    // TODO: send a request and validate Status

    @Test(description = "content-type and status")
    public static void findPetsByStatus() {
        RestAssured.given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");

    }


    @Test(description = "content-type and status")
    public static void storeInventory() {
        RestAssured.given()
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");
    }


    @Test(description = "content-type and status")
    public static void getRequest() {
        RestAssured.given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");
    }

    @Test(description = "content-type and status")
    public static void getRequest2() {
        RestAssured.given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/6")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");
    }
}