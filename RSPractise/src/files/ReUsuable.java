package files;

import io.restassured.path.json.JsonPath;

public class ReUsuable {
	
	public static JsonPath  RawToJson(String result)
	{
		JsonPath json=new JsonPath(result);
		return json;
		
	}

}
