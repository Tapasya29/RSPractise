package jiraautomation;

import static io.restassured.RestAssured.given;

import files.ReUsuable;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class ComplexParsingJIRAAutomation {

	public static void main(String[] args) {
	
			
//Generation of session ID which will be used as Cookies for later login and execution		
			RestAssured.baseURI="http://localhost:8080";
//Creating session Filter			
SessionFilter session=new SessionFilter();

			given().header("Content-Type","application/json")
			.body("{ \"username\": \"swdltest2022\", \"password\": \"April@2022\" }")
			.filter(session)
			.when().post("rest/auth/1/session")
			.then().log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
//creating issue
			String issue=given().header("Content-Type","application/json")
			.body("{\r\n"
					+ "    \"fields\": {\r\n"
					+ "        \"project\": {\r\n"
					+ "            \"key\": \"JUNE\"\r\n"
					+ "        },\r\n"
					+ "        \"summary\": \"Creating Issue using IDE-3\",\r\n"
					+ "        \"description\": \"Using IDE-3\",\r\n"
					+ "        \"issuetype\":{\r\n"
					+ "		  \"name\": \"Bug\"\r\n"
					+ "		  }\r\n"
					+ "    }\r\n"
					+ "}")
			.filter(session)
			.when().post("/rest/api/2/issue")
			.then().log().all()
			.assertThat().statusCode(201)
			.extract().response().asString();
			JsonPath name1=ReUsuable.RawToJson(issue);
			String Issue=name1.get("id");
			String Key=name1.get("key");
						
//adding comment,pulling id
			String commentid=given().header("Content-Type","application/json")
			.pathParam("id",Issue)
			
			.log().all()
					.body("{\r\n"
							+ "    \"body\": \"Sample\",\r\n"
							+ "    \"visibility\": {\r\n"
							+ "        \"type\": \"role\",\r\n"
							+ "        \"value\": \"Administrators\"\r\n"
							+ "    }\r\n"
							+ "}")
			.filter(session)
			.when().post("rest/api/2/issue/{id}/comment")
			.then().log().all()
			.assertThat().statusCode(201).log().all()
			.extract().response().asString();
			JsonPath idcomment=ReUsuable.RawToJson(commentid);
			String commentid1=idcomment.get("id");
			System.out.println(commentid1);
			
						
//Get issue-using both PATH and QUERY parameters
			
			String result=given().header("Content-Type","application/json")
			.pathParam("id",Issue)
			.queryParam("fields","comment")
			.log().all()
			.filter(session)
			.when().get("rest/api/2/issue/{id}")
			.then().log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
			System.out.println(result);	
			JsonPath issues=ReUsuable.RawToJson(result);
			int commentcount=issues.get("fields.comment.comments.size()");
			for(int i=0;i<commentcount;i++)
			{
				System.out.println(issues.getInt("fields.comment.comments["+i+"]"));
				
			}
	
		}

	}

