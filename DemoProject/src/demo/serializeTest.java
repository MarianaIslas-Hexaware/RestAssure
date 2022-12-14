package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class serializeTest {
	
	public static void main (String args[]) {
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	AddPlace ap = new AddPlace();
	ap.setAccuracy(50);
	ap.setAddress("29, side layout, cohen 09");
	ap.setLanguage("French-IN");
	ap.setPhone_number("(+91) 983 893 39837");
	ap.setWebsite("https://rahulshettyacademy.com");
	ap.setName("Frontline house");
	List<String> myList= new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	ap.setTypes(myList); //Set types all the items from the lsit myList
	//Create object for location as setLocation expects a location object
	Location l= new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	ap.setLocation(l);

	Response res =given().queryParam("Key", "qaclick123")
	.body(ap)
	.when()
	.post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).extract().response();
	
	String responseString = res.asString();
	System.out.println(responseString);
	}
}
