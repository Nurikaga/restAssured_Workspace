package api_testing;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class _01_introductionAPI {

// Rest Assured is a java library that is used to perform API Tests

// It uses BDD style such as given, when, then, and ..
// Rest Assured has methods to fetch data from the response body when we make a request.
// As part of Rest Assured we also perform CRUD operations: ( CREATE, READ, UPDATE, DELETE)-> (POST, GET, PUT, DELETE)
// As test Engineers we use mostly GET, POST if needed we use PUT and DELETE

// NOTE: As QA Testers we mostly work with JSON body Payload, we also have exposure to XML.
// JSON EXAMPLE:
// { "name":"TLA" }

// Some of the most used methods in RestAssured are:
// When we make a request:
//      given() ---> used to prepare the request
//      when()  ---> used to send the request
//      then()  ---> used to verify the request
// When we verify a request:
//      prettyPrint() --> used to print the response in pretty format
//      prettyPeek()  --> used to print the response headers, url, in the console in a pretty format
//      log()         --> logs used to print the response
//      asString()    --> Allows us to print the string format.
//      contentType() --> used to verify the content type from the response body and when we send the request
//      accept()      --> used to verify the response from the head when we make a GET request

    //      baseURI       --> used to verify body response from the header when making a Request.
    public static String baseURI = "https://api.octoperf.com/";

    //      Path          --> when we make a request we only provide the path(endpoint) to the specific baseURI
    private String path = "public/users/login";

//      Full URI = https://api.octoperf.com/public/users/login

//      What is an endpoint?
//      An endpoint is a unique URL that represent the object or collect of objects.

//      Query Params?
//            Example: https://www.amazon.com/s?k=christmas+tree
//                           BaseURI       Endpoint   ? Query params

    // Task: Make a http request: POST request for octoperf login
// Unit Tests

    /**
     *  When we verify the response body we also verify the Status codes:
     *  1xx --> Information
     *  2xx --> Success (200 -> OK, 201 -> Created, 204 -> No content)
     *  3xx --> Redirection
     *  4xx --> Client error (400, 401, 403, 404)
     *  5xx --> Server error (500 -> Internal Server error)
     */
    @Test
    public void printResponse() {
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=nicksbastion@yahoo.com&password=motilda1")
                .prettyPrint();
    }

    @Test
    public void printResponse2() {
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=nicksbastion@yahoo.com&password=motilda1")
                .prettyPeek();
    }
}