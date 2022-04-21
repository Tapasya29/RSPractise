import org.testng.Assert;
import files.Payload;
import io.restassured.path.json.JsonPath;

public class CourseDetailsComplexParse {
	public static void main(String[] args) {
//Parsing complex json and mocking the response without calling the endpoint
/*1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount*/
	
//1. Print No of courses returned by API
	
	JsonPath js=new JsonPath(Payload.Course());
	int count=js.getInt("courses.size()");
	//System.out.println(count);
	System.out.println(count);
//2.Print Purchase Amount
	
	int purchaseamount=js.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseamount);
//3.Print title of the first course
	String title=js.getString("courses[0].title");
	System.out.println(title);
//4. Print All course titles and their respective Prices
for(int i=0;i<count;i++)
{
	String title1=js.getString("courses["+i+"].title");
//variable between the string
	int price=js.getInt("courses["+i+"].price");
	System.out.println(title1+price);
	//System.out.println(price);
}
//5. Print no of copies sold by Cypress Course
for(int i=0;i<count;i++)
{
	String title1=js.getString("courses["+i+"].title");
	//if(title1.equalsIgnoreCase("Cypress"))
	if(title1.equalsIgnoreCase("Cypress"))
	{
		int copiycount=js.getInt("courses["+i+"].copies");
		System.out.println(copiycount);
		break;
	}		
	}

//6. Verify if Sum of all Course prices matches with Purchase Amount
int sum=0;
for(int i=0;i<count;i++)
{
	
  int copiesamount=(js.getInt("courses["+i+"].copies")*js.getInt("courses["+i+"].price"));
  System.out.println((copiesamount)); 
  sum=sum+copiesamount;
  }
System.out.println((sum));

Assert.assertEquals(purchaseamount, sum);
}
}
