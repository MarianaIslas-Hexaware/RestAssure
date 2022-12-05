package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class SpecBuilderTest {
	
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
	
	
	ResponseSpecification respspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick")
	.setContentType(ContentType.JSON).build();
	
	RequestSpecification res = given().spec(req)
	.body(ap);
	
	Response response = res.when().post("/maps/api/place/add/json")
	.then().spec(respspec).extract().response();

	
	String responseString = response.asString();
	System.out.println(responseString);
	}
}
