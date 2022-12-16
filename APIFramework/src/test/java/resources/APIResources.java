package resources;
//Enum is a special class in Java that has a collection of methods
public enum APIResources {

	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	//The constructor allows us to asign the resourcec variable from the step definition to the class
	APIResources(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
