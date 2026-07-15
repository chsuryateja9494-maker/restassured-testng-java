package day3;

import static io.restassured.RestAssured.given;

public class LogginDemo {


    public void validateLoggingData(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .statusCode(200)
           //     .log().all()  //to print all the information
           //     .log().cookies()  // to print only cookies
          //      .log().headers();  // to print only headers
                .log().body();   // to print only body
    }
}
