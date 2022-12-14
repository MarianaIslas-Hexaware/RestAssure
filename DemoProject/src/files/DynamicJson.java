package files;

//NewImports
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import files.ReusableMethods;
import files.payload;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI= "http://216.10.245.166";
		String response= given().header("Content-Type","application/json")
		.body(payload.addBook(isbn, aisle))
		.when().post("/Library/AddBook.php")
		.then().assertThat().statusCode(200)
		.extract().response().toString();
		
		JsonPath js= ReusableMethods.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
		
		//delete Book
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//Array collection of elements
		//Multidimensional array is a collection of arrays
		 return new Object[][] {{"abc", "234"}, {"efg", "567"}, {"zyx", "890"}};
	}
	
}
