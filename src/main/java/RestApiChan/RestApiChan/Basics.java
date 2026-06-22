package RestApiChan.RestApiChan;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
		// Validate if add place API is working as expected
		
		//Add place--> Update place with new Address --> Get Place to validate if new address is present in response
		
		//given --> all input details
		// when--> Submit the API-resource, http method
		//then--> validate the response
		//log().all() --> Used to get the input/output in the console
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		 RestAssured.useRelaxedHTTPSValidation();
		
		String response =given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		//{"status":"OK","place_id":"e3424294abc9b2be67f3be1dcb177da5","scope":"APP","reference":"223937941950759585838d4972a32aac223937941950759585838d4972a32aac","id":"223937941950759585838d4972a32aac"}
		
		JsonPath js= new JsonPath(response);//takes string as input and convert into json ie used to parse json
		String placeID =js.getString("place_id");
		
		System.out.println(placeID);
		
		//update place
		String newAddress= "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));//u can also print using json
		
		
		//no need to send header for get request
		//get place
		
		String getPlaceResponse = 
		given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js1 =ReusableMethods.rawToJson(getPlaceResponse);
	
		//JsonPath js1= new JsonPath(getPlaceResponse);
		String actualAddress =js1.getString("address");
		
		System.out.println(actualAddress); 	
		Assert.assertEquals(actualAddress, newAddress);//actual, expected 
		//cucumber, Junit, Testng
		
	}
}
