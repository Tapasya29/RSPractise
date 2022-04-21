import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class UsuageOfTestNg {
	
	@Test
	public void sumofcourses()
	{
		
		JsonPath js=new JsonPath(Payload.Course());
		int count=js.getInt("courses.size()");
	int purchaseamount=js.getInt("dashboard.purchaseAmount");
	System.out.println(count);
	System.out.println(purchaseamount);
	int sum=0;
	for(int i=0;i<count;i++)
	{
		int copiescount=js.getInt("courses["+i+"].copies");
		int price=js.getInt("courses["+i+"].price");
		int res=copiescount*price;
		System.out.println((res)); 
		sum=sum+res;
	  }
	System.out.println((sum));

	Assert.assertEquals(purchaseamount, sum);
	}
	}
