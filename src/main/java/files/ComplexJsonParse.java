package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
        System.out.println(count);//3
        
        //Print Purchase Amount
        int amount =js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);
        
        //Print Title of the first course
        String title=js.getString("courses[0].title");
        System.out.println(title);

        //Print All course titles and their respective Prices
        for(int i=0;i<count;i++) {
        String AllTitle= js.get("courses["+i+"].title");
        int price=js.get("courses["+i+"].price");
        System.out.println(AllTitle+":"+price);
        //or we can print in this way
        System.out.println(js.get("courses["+i+"].price").toString());
        }
        
        //Print no of copies sold by RPA Course
        System.out.println("number of copies sold by RPA Course");
        for(int i=0;i<count;i++) {
        	String CourseTitle = js.get("courses["+i+"].title");
        	if(CourseTitle.equalsIgnoreCase("RPA")) {
        		int copy=js.get("courses["+i+"].copies");
        		System.out.println(copy);
        	 break;
        	
        }}}

        //Verify if Sum of all Course prices matches with Purchase Amount
        //refer SumValidation class
        public void sumOfCourse() {
    		int sum=0;
    		JsonPath js=new JsonPath(payload.CoursePrice());
    		int count=js.getInt("courses.size()");
    		for(int i=0;i<count;i++) {
    			int price= js.get("courses["+i+"].price");
    			int copies=js.get("courses["+i+"].copies");
    			int amount =price*copies;
    			sum =sum+amount;
    		}
    		



}}
