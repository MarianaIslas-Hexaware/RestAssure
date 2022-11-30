import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
		// Validate if add place API working as expected
		//Given -all input details
		//When -submit the API here
		//Then -validate the response
		//content of the file to String: content of file can convert into byte->byte data to string
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		//Retrieving information from a JSON FILE->
		//.body(new String(Files.readAllBytes(Paths.get("URL")))).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		//Output validation
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath js= new JsonPath(response); //Used to parse to Json
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		String newAddress = "Summer Walk, Africa";
		
		//Add place ->Update Place with New Address ->Get place to validate if new address is present in repsonse
		//Update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"+
				"\"place_id\":\""+placeId+"\",\r\n" +
				"\"address\":\""+newAddress+"\", \r\n" +
				"\"key\":\"qaclick123\"\r\n"+
				"}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1= ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress= js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		
	}

}
