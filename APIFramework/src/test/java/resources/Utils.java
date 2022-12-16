package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//Make it static so this variable is shared across all my test cases in the execution 
	static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		if (req== null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\1000075178\\Documents\\Rest Assure\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String resp=  response.asString();
		JsonPath js= new JsonPath(resp);
		return js.get(key).toString();
	}
	
}

//Bring the URL Base path by generating a file and then passing the values 