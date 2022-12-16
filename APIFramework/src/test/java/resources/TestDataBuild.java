package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 39837");
		ap.setWebsite("https://rahulshettyacademy.com");
		ap.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList); // Set types all the items from the lsit myList
		// Create object for location as setLocation expects a location object
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
