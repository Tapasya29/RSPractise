import org.testng.annotations.Test;

import files.Payload;
import files.ReUsuable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {
	
	@Test
	public void addBook()
	{
	
	RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json")
			.body(Payload.book("awsedr","2345"))
	.when().post("Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	
	JsonPath res=ReUsuable.RawToJson(response);
	String id=res.get("ID");
	System.out.println(id);

}
}
