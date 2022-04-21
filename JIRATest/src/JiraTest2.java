import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class JiraTest2 {
	
	public static void main(String[] args)
	{
		
		baseURI="http://localhost:8080";
//creating an session		
	SessionFilter session =new SessionFilter();
given().log().all()
.header("Content-Type","application/json")
.body("{ \"username\": \"Miffy\", \"password\": \"March@2022\" }")
.filter(session)
.when().post("/rest/auth/1/session")
.then().log().all()
.extract().response().asString();

//Creating an issue
String response2=given().header("Content-Type","application/json")
	.body(InputFileforJIRA2.file())
	.filter(session)
	.when()
	.post("/rest/api/2/issue")
				.then().log().all()
				.assertThat().statusCode(201).log().all()
				.extract().response().asString();
System.out.println(response2);
 JsonPath js=new JsonPath(response2);
 String id=js.getString("id");
 
//Getting an issue
 
 given().log().all()
 .header("")
 .when().
		 	
 
	}

}
