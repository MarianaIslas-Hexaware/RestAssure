package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LogInRequest;
import pojo.LoginResponse;

import static io.restassured.RestAssured.given;

import java.io.File;

public class EcommerceAPITest {
	
	public static void main (String[]args) {
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LogInRequest loginRequest = new LogInRequest();
		loginRequest.setUserEmail("marianavives123@gmail.com");
		loginRequest.setUserPassword("A01220787m+");
	
		RequestSpecification reqLogin =given().log().all().spec(req).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract().response()
				.as(LoginResponse.class);
		String token = loginResponse.getToken();
		String userId= loginResponse.getUserId();
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getUserId());

		//Add product
		//Base specification
		//Send form parameters: headers auth token
		RequestSpecification addProductBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
				.build();

		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
		.param("productAddedBy", userId)
		.param("productCategory","fashion")
		.param("productSubCategory","shirts")
		.param("productPrice", "11500")
		.param("productDescription", "Lenova")
		.param("productFor", "men")
		.multiPart("productImage", new File ("C:\\Users\\1000075178\\Documents\\laptop.png"));
		
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath js= new JsonPath(addProductResponse);
		String productId = js.get("productId");
		
		
		//Place an Order
		RequestSpecification createOrderReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();
		
		given().log().all().spec(createOrderReq);
	}

}

