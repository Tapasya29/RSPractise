import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class JiraTest {
	
	public static void main(String[] args)
	{
		
		RestAssured.baseURI="http://localhost:8080";
// here we are generating session id
		
//creating session filter class
		SessionFilter session =new SessionFilter();
// "session" object if used in lines 17-21 then it will store details of session that we are trying to create
//so that object can be reused where ever we want session details that needs to be sent.
	String response=given().log().all()
	.header("Content-Type","application/json")
	.body("{ \"username\": \"Miffy\", \"password\": \"March@2022\" }")
	.filter(session)
	.when().post("/rest/auth/1/session")
	.then().log().all()
	.extract().response().asString();
//	
//	JsonPath js=new JsonPath(response);
//	String sessionid=js.getString("session.value");
//	System.out.println(sessionid);
	
	String response2=given().pathParam("key","10011").header("Content-Type","application/json")
			.body(InputFileforJIRA.file())
			.filter(session)
			.when()
			.post("/rest/api/2/issue/{key}/comment")
				.then().log().all()
				.assertThat().statusCode(201)
				.extract().response().asString();
	System.out.println(response2);


	}

}
