package day3;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class CookiesDemo {

    public void validateCookieData(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .statusCode(200)
                .cookie("AEC",equalTo("AdJVEavUZ0Dj0imB3mvmZwqgcr91zXaDufQgDigGn4UCkQEQVmGfAiEiuQQ"))
                .log().all();
    }


    public void getCookieData(){
       Response res = (Response) given()
                .when()
                .get("https://www.google.com/");
//                .then()
//                .statusCode(200);
               // .cookie("AEC",equalTo("AdJVEavUZ0Dj0imB3mvmZwqgcr91zXaDufQgDigGn4UCkQEQVmGfAiEiuQQ"));
       var oneCookieDetail = res.getCookie("AEC");
        System.out.println("oneCookieDetail = " + oneCookieDetail);
        var multipleCookies = res.getCookies();
        System.out.println("multipleCookies = " + multipleCookies.keySet());
        multipleCookies.entrySet().forEach(System.out::println);
    }
}
