package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LogInRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class EcommerceAPITest {
	
	public static void main (String[]args) {
		
		//Deal with  SSL certification using relaxedHTTPSValidation()method
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LogInRequest loginRequest = new LogInRequest();
		loginRequest.setUserEmail("marianavives123@gmail.com");
		loginRequest.setUserPassword("A01220787m+");
	
		RequestSpecification reqLogin =given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest);
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
		RequestSpecification createOrderBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderId(productId);
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail);
		
		Orders orders= new Orders();
		orders.setOrders(orderDetailList);
		
		RequestSpecification createOrderReq=given().log().all().spec(createOrderBaseReq).body(orders) ;
		String responseAddOrder=createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(responseAddOrder);
		
		//Delete product
		//1. base
		RequestSpecification deleteProductBase= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
				.build();
		
		//2.send base spec
		RequestSpecification deletProdReq= given().log().all().spec(deleteProductBase).pathParam("productId", productId);
		String deleteProductResponse = deletProdReq.when().delete("/api/ecom/product/delete-product/{produtId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(deleteProductResponse);
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
		
		
	}

}

