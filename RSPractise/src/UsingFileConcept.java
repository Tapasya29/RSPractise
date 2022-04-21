import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UsingFileConcept
{
	public static void main(String[] args) throws IOException
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C://Users//tapasya.ditakavi//Documents//Add.json"))))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server",equalTo("Apache/2.4.41 (Ubuntu)"))
		.extract().response().asString();
		System.out.println(response);
	}

}
