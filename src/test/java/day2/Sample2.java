package day2;

/*
Different ways to create POST request body
1) Post request body using Hashmap
2) Post request body creation using using Org. JSON
3) Post request body creation using POJO class
4) Post request using external json file data
*/

import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

public class Sample2 {


    public void createUser(){
        HashMap data = new HashMap();
        data.put("id",1);
        data.put("petId", 1052);
        data.put("quantity", 3);
        data.put("shipDate", "2026-07-04T17:07:26.253Z");
        data.put("status", "placed");
        data.put("complete", true);
        String[] employeearr = {"funny","bunny","sunny"};
        data.put("employees", employeearr);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://petstore.swagger.io/#/store/placeOrder")
                .then()
                .statusCode(200)
                .body("employees[0]",equalTo("funny"))
                .body("employees[1]",equalTo("sunny"))
                .header("Content-Type","application/json")
                .log().all();
    }


    public void createUsingOrgJson(){
        JSONObject data = new JSONObject();
        data.put("id",1);
        data.put("petId", 1052);
        data.put("quantity", 3);
        data.put("shipDate", "2026-07-04T17:07:26.253Z");
        data.put("status", "placed");
        data.put("complete", true);
        String[] employeearr = {"funny","bunny","sunny"};
        data.put("employee", employeearr);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://petstore.swagger.io/#/store/placeOrder")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                .log().all();
    }


    public void createUsingPojo(){
        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setPetId(123);
        data.setQuantity(12);

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://petstore.swagger.io/#/store/placeOrder")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                .log().all();
    }


    public void createUsingJsonFile() throws FileNotFoundException {
        //The java.io.File class is an abstract representation of file and directory paths
        File f = new File(".\\src\\test\\resources\\testdata\\body.json");
        FileReader fr = new FileReader(f); //this is to read the file
        JSONObject data = new JSONObject(); //this is to get the data, which is in json format for post request payload

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://petstore.swagger.io/#/store/placeOrder")
                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                .log().all();
    }
}
