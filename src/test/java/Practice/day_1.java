package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class day_1 {

    // TASK: send a request for Authors GET request
    @Test
    public void getAuthors() {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");

    }

    @Test
    public void getAuthorsPrettyPeek() {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");


    }

    @Test
    public void getActivities() {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");

}
    @Test
    public void getActivitiesPrettyPeek() {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Activities")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");

}
// Task: Validate response status code for log in

    @Test
    public void verifyStatusCode(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=nicksbastion@yahoo.com&password=motilda1")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");



    }
   // Task: Validate from the response body the Content-type = JSON
    @Test
    public void verifyContentTypeJson(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=nicksbastion@yahoo.com&password=motilda1")
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully");

    }

//
}