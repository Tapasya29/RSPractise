import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReUsuable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class UsingDataProvider 
{
	
	@Test(dataProvider="listdata")
	public void addBook(String name,String isbn)
	{
	
	RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json")
			.body(Payload.book(name,isbn))
	.when().post("Library/Addbook.php")
	.then().log().all().
	assertThat().statusCode(200)
	.extract().response().asString();
	
	JsonPath res=ReUsuable.RawToJson(response);
	String id=res.get("ID");
	System.out.println(id);
	}
	@DataProvider(name="listdata")
	public Object[][] input()
	{
		return new Object[][] {{"wmlkgw","12"},{"ojlvbw","13"}};
//each array holds data of one book
	}
//Deleting added Data	
	@Test(dataProvider="listdata")
	public void DeleteBook(String name,String isbn)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		given().header("Content-Type","application/json")
		.body(Payload.book(name, isbn))
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).
		extract().response().asString();
	}
}
