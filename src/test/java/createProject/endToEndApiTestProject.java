package createProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpStatus.*;

public class endToEndApiTestProject {

    String memberOf = "/workspaces/member-of";
    String Id;
    String userId;

    Object name;

    Object description;

    Object lastModified;

    Object created;
    Map<String, Object> variables;
    Response response;

    // In TestNG what is one of the annotations that allows us to runs the Tests before each Test
    @BeforeTest
    public String token(){
        RestAssured.baseURI = "https://api.octoperf.com";
        String path = "/public/users/login";

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username", "nicksbastion@yahoo.com");
        map.put("password", "motilda1");

        return RestAssured.given()
                .queryParams(map)
                .when()
                .post(path)
                .then()
                .statusCode(SC_OK)
                .extract() //Method that extracts the response JSON DATA
                .body() // Body extracted as a JSON format
                .jsonPath() // Navigate using jsonPath
                .get("token"); // Get the value as a key token
    }
    @Test
    public void createNewWorkspace() {
        String requestBody = "{\"id\":\"\",\"userId\":\"\",\"name\":\"HelloWorld\",\"description\":\"addd\",\"created\":\"2022-11-14T21:29:32.771Z\",\"lastModified\":\"2022-11-14T21:29:32.771Z\"}";
        response = RestAssured.given()
                .header("Content-type", "application/json")
                .header("Authorization", token())
                .and()
                .body(requestBody)
                .when()
                .post("/workspaces")
                .then().log().all()
                .extract()
                .response();

        Assert.assertEquals(SC_CREATED, response.statusCode());
        variables = new HashMap();
        variables.put("workspaceId", response.jsonPath().getString("id"));
        System.out.println(response.jsonPath().getString("id[1]"));
        System.out.println(response.jsonPath().getString("userId"));

    }

    @Test
    public void memberOf(){
        response = RestAssured.given()
                .header("Authorization",token())
                .when()
                .get(memberOf)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();
        System.out.println(response.jsonPath().getString("name[0]"));
        Assert.assertEquals(SC_OK,response.statusCode());

        Id = response.jsonPath().getString("id[1]");
        userId = response.jsonPath().getString("userId[1]");

        System.out.println(Id);
        System.out.println(userId);

        variables.put("id",Id);
        variables.put("userID", userId);
        variables.put("workspaceName", name);
        variables.put("description", description);
        variables.put("created", created);
        variables.put("lastModified", lastModified);

        Assert.assertEquals("Default", response.jsonPath().getString("name[0]"));
        Assert.assertEquals("DxUgpYQB-D5gaM8BYdC3", response.jsonPath().getString("id[0]"));
        Assert.assertEquals("h-FucoABntJBI2z642tS", response.jsonPath().getString("userId[0]"));
        Assert.assertEquals("1669217673654", response.jsonPath().getString("lastModified[0]"));

        userId = response.jsonPath().getString("userId[1]");
        Id = response.jsonPath().getString("workspaceId[1]");
        name = response.jsonPath().getString("workspaceName[1]");
        description = response.jsonPath().getString("description[1]");
        created = response.jsonPath().getInt("created[1]");
        lastModified = response.jsonPath().getInt("lastModified[1]");

        Assert.assertEquals(Id, response.jsonPath().getString("workspaceId[1]"));
        Assert.assertEquals(userId, response.jsonPath().getString("userId[1]"));
        Assert.assertEquals(name, response.jsonPath().getString("workspaceName[1]"));
        Assert.assertEquals(description, response.jsonPath().getString("description[1]"));
        Assert.assertEquals(created, response.jsonPath().getInt("created[1]"));
        Assert.assertEquals(lastModified, response.jsonPath().getInt("lastModified[1]"));
    }

    @Test(dependsOnMethods = {"memberOf"})
    public void createProject(){
        System.out.println(variables.get("id"));
        System.out.println(variables.get("userID"));

        // Provide Json Payload in String format
        String requestBody = "{\"id\":\"\",\"created\":\"2022-11-09T01:58:29.666Z\",\"lastModified\":\"2001-11-09T01:58:29.666Z\",\"userId\":\""+variables.get("userID")+"\",\"workspaceId\":\""+variables.get("id")+"\",\"name\":\"I did not get the shoes\",\"description\":\"sdfasdfasd\",\"type\":\"DESIGN\",\"tags\":[]}";

        System.out.println(requestBody);
        response = RestAssured.given()
                .headers("Content-type","application/json")
                .header("Authorization",token())
                .and()
                .body(requestBody)
                .when()
                .post("/design/projects")
                .then().log().all()
                .extract()
                .response();
        Assert.assertEquals(SC_CREATED, response.statusCode());

        Assert.assertEquals("I did not get the shoes",response.jsonPath().getString("name"));
        Assert.assertEquals("sdfasdfasd",response.jsonPath().getString("description"));
        System.out.print(response.jsonPath().getString("id"));
        variables.put("projectId",response.jsonPath().getString("id"));

    }

    @Test(dependsOnMethods = {"createNewWorkspace", "memberOf","createProject"})
    public void updateProject(){
        String updateProject = "{\"id\":\""+variables.get("projectId")+"\",\"created\":\"2022-11-09T01:58:29.666Z\",\"lastModified\":\"2001-11-09T01:58:29.666Z\",\"userId\":\""+variables.get("userID")+"\",\"workspaceId\":\""+variables.get("id")+"\",\"name\":\"I GOT THE SHOES :)\",\"description\":\"sdfasdfasd\",\"type\":\"DESIGN\",\"tags\":[]}";

        System.out.println(updateProject);

        RestAssured.given()
                .header("Content-type", "application/json")
                .header("Authorization", token())
                .and()
                .body(updateProject)
                .when()
                .put("/design/projects/"+variables.get("projectId"))
                .then()
                .log().all();
        Assert.assertEquals(SC_CREATED, response.statusCode());
    }


    @Test(dependsOnMethods = {"createNewWorkspace","memberOf", "createProject", "updateProject"})
    public void deleteProject() {
        response = RestAssured.given()
                .header("Authorization", token())
                .when()
                .delete("/design/projects/" + variables.get("projectId"))
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Deleted: " + response);
         Assert.assertEquals(SC_NO_CONTENT, response.statusCode());

    }

    @Test(dependsOnMethods = {"createNewWorkspace", "memberOf", "createProject", "updateProject", "deleteProject"})
    public void deleteWorkspace() {
        response = RestAssured.given()
                .header("Authorization", token())
                .when()
                .delete("/workspaces/" + variables.get("workspaceId"))
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Deleted: " + response);
        Assert.assertEquals(SC_NO_CONTENT, response.statusCode());

    }

}
