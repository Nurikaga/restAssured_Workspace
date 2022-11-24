package Practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Authors {
    @Test
    public void getCharts() {
        RestAssured.given()
                .when()
                .get("https://fakerestapi.azurewebsites.net/api/v1/Authors")
                //.prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
        System.out.println("Verified Content-type Json successfully and status code");
    }
    @Test
    public void enterFirstNameAndLastName(){
        RestAssured.baseURI="https://fakerestapi.azurewebsites.net";
        String path = "/api/v1/Authors";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("firstName", "Nur");
        map.put("lastName", "Zhekebatyrov");
//                key         value
        RestAssured.given()
                .queryParams(map)
                .when()
                .post(path)
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200)
                .log().all();
    }

    }
