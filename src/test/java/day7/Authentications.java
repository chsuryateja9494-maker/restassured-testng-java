package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {

@Test
    public void testEmptiveAuthentication(){  //this is for postman
        //preemptive() is used with Basic Authentication to send the username and password immediately with the first request,
        // instead of waiting for the server to challenge with a 401 Unauthorized response.
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

@Test
    public void bearerTokenAuthentication(){  //this is used for Github
      //  String bearer = "xyz";  //here bearer keyword is used
        String bearer = System.getenv("GITHUB_TOKEN");
        given()
                .header("Authorization", "Bearer " + bearer)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(404)
                .log().all();
    }

    public void oAuth2Authentication(){  //this is used for Github   //here bearer keyword is not used
        String bearer = System.getenv("GITHUB_TOKEN");
        given()
                .auth().oauth2(bearer)
                .when()
                .get("https://api.github.com/users/repos")
                .then()
                .statusCode(404)
                .log().all();
    }

    @Test
    public void apiKeyAuthentication(){  //appid is the api key
        String bearer = System.getenv("GITHUB_TOKEN");
        given()
                .queryParam("appid",bearer)
                .when()
                .get("https://openweathermap.org")
                .then()
                .statusCode(404)
                .log().all();
    }



}
