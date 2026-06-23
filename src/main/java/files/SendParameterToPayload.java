package files;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class SendParameterToPayload {
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().log().all().header("Content-Type","application/json").
		body(payload.AddBook1(isbn,aisle)).
		when()
		.post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReusableMethods.rawToJson(response);
		String id=js.get("ID");
		System.out.println(id);
	}
	
	@Test
	public void addBook() 
	{System.out.println("branch test"); }
	
   @DataProvider(name="BooksData")
   public Object[][] getData()
   {
	//Array=collection of elements
	//Multidimentional Array=collection of Array
	return new Object[] [] {{"chshsn","9844"},{"nhshsn","9513"},{"phshsn","8144"}};
   }


	
}


