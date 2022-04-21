package jiraautomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsuable;

public class SessionCreation {

	public static void main(String[] args) {
		
//Generation of session ID which will be used as Cookies for later login and execution		
		RestAssured.baseURI="http://localhost:8080";
		String response=given().header("Content-Type","application/json")
		.body("{ \"username\": \"swdltest2022\", \"password\": \"April@2022\" }")
		.when().post("rest/auth/1/session")
		.then().log().all()
		.assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath name1=ReUsuable.RawToJson(response);
		JsonPath value1=ReUsuable.RawToJson(response);
		String name2=name1.get("name");
		String value2=value1.get("value");
		System.out.println(value2);
		
		
	}

}
