package day6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;

public class JsonSchemaValidation {


    public void schemaValidation(){
     given()
             .when()
             .get("http://localhost:8080/store")
             .then()
             .assertThat().body(JsonSchemaValidator.matchesJsonSchema("samepleschema.json"));
    }


}
