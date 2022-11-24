package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class _02_LoginTypes {

    /**
     * Log in to octoperf and use full path url to perform a sign in
     */

    @Test
    public void testUsingFillPath(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=nicksbastion@yahoo.com&password=motilda1")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    /**
     * Log in Using Map to verify contents type and status code
     * Map stores => key and value pairs Hashmap implements Maps.
     */
    @Test
    public void useMapsToLogIn(){
        RestAssured.baseURI="https://api.octoperf.com";
        String path = "public/users/login";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username", "nicksbastion@yahoo.com");
        map.put("password", "motilda1");
//                key         value
        RestAssured.given()
                .queryParams(map)
                .when()
                .post(path)
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200);
    }

    /**
     * Log in using query param
     */

    @Test
    public void useParam(){
        RestAssured.baseURI="https://api.octoperf.com";
        String path = "public/users/login";

        RestAssured.given()
                .queryParam("username","nicksbastion@yahoo.com")
                .queryParam("password","motilda1")
                .when()
                .post(path)
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .statusCode(200)
                .and()
                .log().all();

    }

}
