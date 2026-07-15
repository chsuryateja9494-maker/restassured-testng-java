package day3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PathAndQueryParams {


    public void queryAndPathParams(){
        given()    //mypath is variable name, it can be of any name
                .pathParams("version","v2","myPath","user/login")
                .queryParam("username","asa")
                .queryParam("password","545")
                .when()
                .get("https://petstore.swagger.io/{version}/user/{myPath}")
                //the query parameters qwill go along with this url, it's an optional to put them in url
                .then()
                .statusCode(200)
                .body("type",equalTo("unknown"));
    }}
