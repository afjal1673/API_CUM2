package step_definitions;

import java.util.Random;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStore_User_Collection {
	
	int id;
	String username;
	String email;
	
	@Given("I generate all test random value")
	public void i_generate_all_test_random_value() {
		//Generate Random id,username,and email
	    id=new Random().nextInt(10000);
	    username="testuser"+id;
	    email="Test_Email"+id+"@gmail.com";
	    
	    //Log Generated Values
//	    System.out.println(id);
//	    System.out.println(username);
//	    System.out.println(email);
	    
	    //Set up Base URL
	    RestAssured.baseURI="https://petstore.swagger.io/v2";    
	}
	@When("I Create a User")
	public void i_Create_a_User() {
	   JSONObject reqBody=new JSONObject();
	   reqBody.put("id", id);
	   reqBody.put("username",username );
	   reqBody.put("firstName", "abu");
	   reqBody.put("lastName", "bokkor");
	   reqBody.put("email", email);
	   reqBody.put("phone", "014788");
	   
	   //Send Post request to create a user
	   Response response=(Response) RestAssured
			   .given()
			   .contentType(ContentType.JSON)
			   .body(reqBody.toString())
			   .when()
			   .post("/user");	 
	   //Print Response 
	   System.out.println("POST Response Code: "+response.getStatusCode());
	   System.out.println("POST Response Body: "+response.getBody().asString());
	   
	   //Validate the response
	   Assert.assertEquals(response.getStatusCode(),200);
	  // Assert.assertTrue(response.getBody().asString().contains(username));
	 //  Assert.assertTrue(response.getBody().asString().contains(email));
	}
	@Then("I GetUser")
	public void i_GetUser() {
		// send  Get Request to retrive the created user
		 
		Response response = RestAssured
				.given()
				.pathParam("username", username)
				.when()
				.get("/user/{username}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString()); 
		Assert.assertEquals(response.getStatusCode(),200);
}
	@Then("UpdateUser")
	public void updateuser() {
		 JSONObject reqBody=new JSONObject();
		   reqBody.put("id", id);
		   reqBody.put("username",username );
		   reqBody.put("firstName", "md");
		   reqBody.put("lastName", "Roni");
		   reqBody.put("email", email);
		   reqBody.put("phone", "01478898653");
		 //Send Update request to create a user
		   Response response=(Response) RestAssured
				   .given()
				   .contentType(ContentType.JSON)
				   .body(reqBody.toString())
				   .pathParam("username",username)
				   .when()
				   .put("/user/{username}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	@Then("CheckUpdate")
	public void checkupdate() {
		// send  Get Request to retrive the created user
		 
				Response response = RestAssured
						.given()
						.pathParam("username", username)
						.when()
						.get("/user/{username}");
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody().asString());
				Assert.assertEquals(response.getStatusCode(),200);
	}
	@Then("DeleteUser")
	public void deleteuser() {
		Response response = RestAssured
				.given()
				.pathParam("username", username)
				.when()
				.delete("/user/{username}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),200);
	}

	@Then("TestDelete")
	public void testdelete() {
		Response response = RestAssured
				.given()
				.pathParam("username", username)
				.when()
				.get("/user/{username}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(),404);
	    
	}

}