package jiraautomation;

import static io.restassured.RestAssured.given;

import java.io.File;

import files.ReUsuable;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class WithoutSessionFilter {

	public static void main(String[] args) {
	
			
//Generation of session ID which will be used as Cookies for later login and execution		
			RestAssured.baseURI="http://localhost:8080";
//Creating session Filter			
//SessionFilter session=new SessionFilter();

			String SessionId=given().header("Content-Type","application/json")
			.body("{ \"username\": \"swdltest2022\", \"password\": \"April@2022\" }")
			//.filter(session)
			.when().post("rest/auth/1/session")
			.then().log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
			JsonPath sessionid=ReUsuable.RawToJson(SessionId);
			String idsession1=sessionid.getString("session.name");
			String idsession2=sessionid.getString("session.value");
//			System.out.println(idsession1);
//			System.out.println(idsession2);
			String sessionkey=(idsession1+"="+idsession2);
			System.out.println(sessionkey);

//creating issue
			String issue=given().header("Content-Type","application/json")
			.header("Cookie",sessionkey)
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
			//.filter(session)
			.when().post("/rest/api/2/issue")
			.then().log().all()
			.assertThat().statusCode(201)
			.extract().response().asString();
			JsonPath name1=ReUsuable.RawToJson(issue);
			String Issue=name1.get("id");
			String Key=name1.get("key");
						
//adding comment
			given().header("Content-Type","application/json")
			.header("Cookie",sessionkey)
			.pathParam("id",Issue)
			.log().all()
					.body("{\r\n"
							+ "    \"body\": \"Sample\",\r\n"
							+ "    \"visibility\": {\r\n"
							+ "        \"type\": \"role\",\r\n"
							+ "        \"value\": \"Administrators\"\r\n"
							+ "    }\r\n"
							+ "}")
			//.filter(session)
			.when().post("rest/api/2/issue/{id}/comment")
			.then().log().all()
			.assertThat().statusCode(201)
			.extract().response().asString();
//Adding attachment
			given().header("Content-Type","multipart/form-data")
			.header("X-Atlassian-Token","no-check")
			.header("Cookie",sessionkey)
			.pathParam("id",Issue)
			.multiPart("file",new File("sample.txt"))
			.log().all()
					.body("{\r\n"
							+ "    \"body\": \"Sample\",\r\n"
							+ "    \"visibility\": {\r\n"
							+ "        \"type\": \"role\",\r\n"
							+ "        \"value\": \"Administrators\"\r\n"
							+ "    }\r\n"
							+ "}")
			
			.when().post("rest/api/2/issue/{id}/attachments")
			.then().log().all()
			.assertThat().statusCode(200)
			.extract().response().asString();
		
		}

	}

