package files;

public class Payload {
	
	public static String AddPlace()
	{
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 52,\r\n"
				+ "  \"name\": \"Updating address2\",\r\n"
				+ "  \"phone_number\": \"(+91) 666 864 9876\",\r\n"
				+ "  \"address\": \"7656, right,789\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String Course()
	{
		return "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "  \"purchaseAmount\": 1060,\r\n"
				+ "  \"website\": \"rahulshettyacademy.com\"  \r\n"
				+ "},\r\n"
				+ "\"courses\":[\r\n"
				+ "{\r\n"
				+ "\"title\":\"cc\",\r\n"
				+ "\"price\":50,\r\n"
				+ "\"copies\":6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\":\"cypress\",\r\n"
				+ "\"price\":40,\r\n"
				+ "\"copies\":7\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "  \"title\":\"java\",\r\n"
				+ "  \"price\":60,\r\n"
				+ "  \"copies\":8\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}";
	}
	
	public static String book(String name,String isbn)
	{
		String data="{\r\n"
				+ "\r\n"
				+ "\"name\":\""+name+"\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\"34568\",\r\n"
				+ "\"author\":\"Johnmiller\"\r\n"
				+ "}";
		return data;
	}
}
