

package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.File;

public class FileUploadAndDownload {

    public void uploadFile(){
        File myFile = new File("src/test/resources/testdata/Test1");

        given()
                .multiPart("file",myFile)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadfile")
        .then()
                .statusCode(201)
                .body("fileName", equalTo("Test1.txt"))
                .log().all();
    }


    public void uploadMultipleFiles(){
        File myFile1 = new File("src/test/resources/testdata/Test1");
        File myFile2 = new File("src/test/resources/testdata/Test2");

        File[] myfilesarr = {myFile1, myFile2};
        given()
                .multiPart("file",myfilesarr)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadmultiplefiles")
        .then()
                .statusCode(201)
                .body("fileName", equalTo("Test1.txt"))
                .log().all();
    }


    public void fileDownload(){
        given()
                .when()
                .get("http://localhost:8080/downloadfiles/Test2")
        .then()
                .statusCode(200)
                .log().body();
    }
}
