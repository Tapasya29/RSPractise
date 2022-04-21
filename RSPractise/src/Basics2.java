import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Payload;
public class Basics2 {

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
//Update the Address
	
	given().queryParam("key","qaclick123")
	.header("Content-Type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+id+"\",\r\n"
			+ "\"address\":\"70 winter walks, USA\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "")
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200)
	.body("msg",equalTo("Address successfully updated"));
	
//------------------------------------------------------------------------------//
//Get updated ID details
	
	given().queryParam("key","qaclick123")
	.queryParam("place_id", id)
	.when().get("maps/api/place/get/json")
	.then().assertThat().statusCode(200).log().all();
	}

}
