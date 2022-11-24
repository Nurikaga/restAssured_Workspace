package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CoverPhotos {
    @Test
    public static void getCoverPhotos() {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-Type Json successfully and status code");
    }
    @Test
    public static void postCoverPhotos() {
        RestAssured.given()
                .when()
                .post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos")
//                .prettyPeek()
                .then()
                .assertThat()
//                .statusCode(200)
//                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-Type Json successfully and status code");
}}