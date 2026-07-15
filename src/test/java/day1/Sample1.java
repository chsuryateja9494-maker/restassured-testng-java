package day1;

import org.testng.annotations.Test;

import java.util.*;
import static io.restassured.RestAssured.given;


public class Sample1 {

    int id;

    @Test(priority = 1)
    public void getUser() {

        given()
                .when()
              //  .get("https://reqres.in/api/collections?project_id=33591")
                .get("https://petstore.swagger.io/#/store/getInventory")
                .then()
                .statusCode(200)
              //  .body("page",equalTo(2))
                .log().all();
    }


    @Test(priority = 2)
    public void createUser(){
        HashMap data = new HashMap();
        data.put("petId",123);
        data.put("quantity",5);

        id = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://petstore.swagger.io/#/store/placeOrder")
                .jsonPath().getInt("id");
//                .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 3)
    public void updateUser(){
        HashMap data = new HashMap();
        data.put("petId",123);
        data.put("quantity",6);

         given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://petstore.swagger.io/#/store/placeOrder")
                .then()
                .statusCode(201)
                .log().all();
    }




}
