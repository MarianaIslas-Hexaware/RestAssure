import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumValidation() {
		//Verify if sum of all courses matches with purchase amount
		JsonPath js = new JsonPath(payload.CoursePrice());
		int count = js.getInt("courses.size()");
		for (int i=0; i<count; i++) {
			int prices = js.getInt("courses["+i+"].price") ;
			int copies=js.get("courses["+i+"].copies");
			int amount= prices*copies;
			System.out.println(amount);
		}
	}
}
