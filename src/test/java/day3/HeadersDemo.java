package day3;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HeadersDemo {


    public void getHeaderDetails() {

        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .statusCode(200)
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .log().all(); //prints all the information
        // .log().headers(); -> prints only the headers
    }


    public void getHeaders() {
        Response res = given()
                .when()
                .get("https://www.google.com/");
        var oneHeader = res.getHeader("Content-Type");
        System.out.println("oneHeader = " + oneHeader);
        var multipleHeaders = res.getHeaders();  //we had not used entrySet(0 fpr headers as it return objects not map
        //   System.out.println("multipleHeaders = " + multipleHeaders);
        multipleHeaders.asList().forEach(System.out::println); //it belongs to "io.restassured.http.Headers" package.
    }

    public void printHeaders() {
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .log().headers();
    }


}
