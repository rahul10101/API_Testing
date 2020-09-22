package stepdefs.api;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import java.util.HashMap;

import context.TestBase;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class PostRequestStepDefs extends TestBase{
	
	//RequestSpecification req_spec = null;
	//Response resp = null;
	
	Scenario scn;
	
	@Before
	public void setUp(Scenario s) {
		this.scn = s;
	}
	String email = GetRandomString(10) + "gmail.com";
	
	@Given("I set header and body to create new user")
	public void i_set_header_and_body_to_create_new_user() {
	  
	  HashMap<String,String> hm_header = new HashMap<String, String>();
	  hm_header.put("Content-Type", "application/json");
	  
	  String body_string = "{ \"name\": \"Rwefsdau ki\",\r\n" + 
	  		"        \"email\": \""+email+",\r\n" + 
	  		"        \"gender\": \"Female\",\r\n" + 
	  		"        \"status\": \"Active\"}";
	  
	  req_spec.headers(hm_header).body(body_string);
	}

	@When("I hit the api with post request and end point as {string}")
	public void i_hit_the_api_with_post_request_and_end_point_as(String endPoint) {
	 resp = req_spec.when().post(endPoint);
	}

	@Then("API created a new User in the system")
	public void api_created_a_new_User_in_the_system() {
		resp.then().assertThat()
		//.statusCode(302)
		.body("code", equalTo(201))
		.body("meta", equalTo(null))
		.body("data.name",equalTo("Rwefsdau ki"))
		.body("data.email", equalTo(email))
		.body("data.gender",equalTo("Female"))
		.body("data.status",equalTo("Active"));				
	}

	@Then("I can find the new user in the system when i get the user")
	public void i_can_find_the_new_user_in_the_system_when_i_get_the_user() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


}
