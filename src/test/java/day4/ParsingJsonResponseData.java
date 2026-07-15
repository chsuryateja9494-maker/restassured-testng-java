package day4;


import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.stream.Stream;

public class ParsingJsonResponseData {
//parsing means traversing through Json response to get required fields or required data from Json
    //By using JSONObject class we can parse Json data
    //JsonPath is an alternative to using XPath for easily getting values from a Object document.


    public void validateJsonDataWithJsonPathFindert() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8000/")
                .then()
                .statusCode(200)
                .header("Content-Type", "text/html")  // & ".contentType(ContentType.JSON)" , both are same
                .body("data.employees[1].name", equalTo("Bob Smith"));


//        System.out.println("res: "+ res.asPrettyString());
//        System.out.println("res: "+ res.jsonPath().getString("data.employees[0].name"));
//        var getres = res.
//        System.out.println("getres = " + getres.contains("Bob Smith"));


//        String json = res.asString();
//        JsonPath jp = new JsonPath(json);
//        System.out.println("res: " + jp.prettyPeek());
        //System.out.println("res: " + jp.getString("data.employees[0].name"));
    }


    public void validateJsonDataUsingStorage() {
      //  RestAssured.defaultParser = Parser.JSON;
        RestAssured.registerParser("text/html", Parser.JSON);

        Response res =
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8000/");

        //RestAssured.defaultParser = Parser.JSON;

//        System.out.println("res: "+ res.asPrettyString());
//        System.out.println("res: "+ res.jsonPath().getString("data.employees[0].name"));
//        var getres = res.asPrettyString();
//        System.out.println("getres = " + getres.contains("Bob Smith"));

        Assert.assertEquals(res.getStatusCode(),200);
//        String employyeName = res.jsonPath().getString("data.employees[1].name");
        //Another way to above line

//        Assert.assertEquals(employyeName,"Bob Smith");

        //to get all the employee names in that array "getlist" is used
        JsonPath jp = new JsonPath(res.asString());  //JSON Object converting to string as it is object format
        var employeeList = jp.getList("data.employees.name", String.class);
        employeeList.forEach(System.out::println); //JsonPath is an alternative to using XPath for easily getting values from a Object document.
//        Here, "String.class" tells Rest Assured:
//        "Extract the values and convert each one to a String."
        Assert.assertTrue(employeeList.contains("Bob Smith"));

        //to get sum of all ID's
        int totalPrice = jp.getList("data.employees.id", Integer.class).stream().reduce(0, Integer::sum);
        System.out.println("Total Price = " + totalPrice);

      /*  How to get a value using JsonObject or JsonArray or JsonElement
      If it starts with object("{"), then
      JSONObject jo = new JSONObject(res.asString());
      var employeeList = jo.getJSONArray("arr name").getJSONObject(object count).get("title");

      If it starts with array("["), then
      JSONArray jo = new JSONArray(res.asString());
      var employeeList = jo.getJSONObject("arr name").getJSONArray(object count).get("title");

      A JSON array can contain multiple arrays, a JSON object can contain multiple objects and viceversa
       */
    }


}
