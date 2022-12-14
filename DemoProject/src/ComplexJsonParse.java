import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		//Print purchase amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		//Print title of the first course
		String title = js.getString("courses[0].title");
		System.out.println(title);
		//Print all courses titles and their respective prices
		for(int i=0; i<count; i++) {
			String coursesTitles= js.get("courses["+i+"].title");
			int prices = js.getInt("courses["+i+"].price") ;
			System.out.println(coursesTitles + " " + prices);
		}
		//Print number of copies sold by RP course
		System.out.println("Print number of copies sold by RPA course");
		
		for(int i=0;i<count; i++){
			String coursesTitles= js.get("courses["+i+"].title");
			if(coursesTitles.equalsIgnoreCase("RPA")) {
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		//Verify if sum of all courses matches with purchase amount
		int sum = 0;
		for (int i=0; i<count; i++) {
			int prices = js.getInt("courses["+i+"].price") ;
			int copies=js.get("courses["+i+"].copies");
			int amount= prices*copies;
			System.out.println(amount);
			sum = sum+amount;
		}
		System.out.println("The total amount is: " + sum);
		int purchaseAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
	}

}
