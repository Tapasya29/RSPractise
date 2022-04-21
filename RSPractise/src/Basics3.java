import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
public class Basics3 {

	public static void main(String[] args) {
		//given-all input details
		//when-submit the API
		//then-validate the output
//---------------------------------------------------------------------//
//Adding new Place		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server",equalTo("Apache/2.4.41 (Ubuntu)"))
		.extract().response().asString();
		System.out.println(response);
//response has output and we want to extract place_id from output
//Parsing JSON using JsonPath and it takes input as String
		JsonPath json=new JsonPath(response);
		String id=json.getString("place_id");
//here in line 29 we should give parameter value from response which we want to print
		System.out.println(id);
//--------------------------------------------------------------------------//
//Update the Address by declaring the value rather than hard coding
		
		String add="SouthAfrica";
	
	given().queryParam("key","qaclick123")
	.header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+id+"\",\r\n"
			+ "\"address\":\""+add+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "")
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200)
	.body("msg",equalTo("Address successfully updated"));
	
//------------------------------------------------------------------------------//
//Get updated address details
	
	String resadd=given().queryParam("key","qaclick123")
	.queryParam("place_id", id)
	.when().get("maps/api/place/get/json")
	.then().assertThat().statusCode(200).log().all()
	.extract().response().asString();
	
	JsonPath res2=new JsonPath(resadd);
	String updatedaddress=res2.getString("address");
	System.out.println(updatedaddress);
	
//validate using Test NG for checking updated address
	Assert.assertEquals(add, updatedaddress);
}
}
