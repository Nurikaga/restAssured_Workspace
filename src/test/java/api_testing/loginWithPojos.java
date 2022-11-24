package api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojos.LoginPojos;

public class loginWithPojos {

    LoginPojos pojo = new LoginPojos();


    @Test
    public void loginPojos(){
        pojo.setUserName("nicksbastion@yahoo.com");
        pojo.setPassWord("motilda1");

        RestAssured.baseURI="https://api.octoperf.com";
        String path = "public/users/login";

        RestAssured.given()
                .queryParam("username",pojo.getUserName())
                .queryParam("password", pojo.getPassWord())
                .when()
                .post(path)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .log().all();

    }

}
