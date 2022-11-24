package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class Aviation {
    @Test
    public void getCharts() {
        RestAssured.given()
                .when()
                .get("https://api.aviationapi.com/v1/charts?apt=DCA")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");

    }

    @Test
    public void getChartsChanges() {
        RestAssured.given()
                .when()
                .get("https://api.aviationapi.com/v1/charts/changes?apt=CLT")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");

}

    @Test
    public static void getAirports(){
        RestAssured.given()
                .when()
                .get("https://api.aviationapi.com/v1/airports?apt=DCA")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    System.out.println("Verified Content-type Json successfully and status code");

}
    @Test
    public static void getPilots(){
        RestAssured.given()
                .when()
                .get("https://api.aviationapi.com/v1/vatsim/pilots?apt=dca")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    System.out.println("Verify Content-type Json successfully and status code");
}
}