package stepdefs.api;

import static io.restassured.RestAssured.given;

import context.TestBase;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
//import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;

public class GetRequestStepdef extends TestBase {

	String server = LoadProperties().getProperty("url");
	String accessToken = LoadProperties().getProperty("token");
	//RequestSpecification req_spec = null;
	//Response resp = null;
	Scenario scn;

	@Before
	public void setUp(Scenario s) {
		this.scn = s;
	}

	@Given("Go rest API is up and running")
	public void go_rest_API_is_up_and_running() {
		req_spec = given().baseUri(server).auth().oauth2(accessToken);

	}

	@When("I hit the api with get request and end point as {string}")
	public void i_hit_the_api_with_get_request_and_end_point_as(String endPoint) {
		resp = req_spec.when().get(endPoint);
		scn.write("Response of the request is: " + resp.asString() + "<br>");
	}

	@Then("API should return all the users")
	public void api_should_return_all_the_users() {
		resp.then().assertThat().statusCode(200)
				// .body("meta.pagination.pages", equalTo(78))
				.body("meta.pagination.limit", equalTo(20))
				// .body("data[0].id",equalTo(13))
				// .body("data[0].name",equalTo("Govind Swaroop"))
				.body("result", not(emptyArray()));
	}

	@Then("API should return all the users on page {int} Only")
	public void api_should_return_all_the_users_with_specific_pagination_value(int pageNumber) {
		resp.then().assertThat().statusCode(200)
				.body("meta.pagination.page", equalTo(5))
				.body("meta.pagination.limit", equalTo(20))
				.body("result", not(emptyArray()));
	}

	@Then("API should return user details of user id {string}")
	public void api_should_return_user_details_of_user_id(String user_id) {
		resp.then().assertThat().statusCode(200)
				.body("code", equalTo(200)).body("meta", equalTo(null))
				.body("data.id", equalTo(645))
				.body("result", not(emptyArray()));

	}
	@Then("API should return user not found response for id {string}")
	public void api_should_return_user_not_found_response_for_id(String user_id) {
		resp.then().assertThat().statusCode(200)
		.body("code", equalTo(404))
		.body("meta", equalTo(null))
		.body("data.message", equalTo("Resource not found"));
	}

	@Then("API should return all the female users")
	public void api_should_return_all_the_female_users() {
		resp.then().assertThat().statusCode(200).body("code", equalTo(200))
		.body("meta.pagination.pages", equalTo(32))
		.body("meta.pagination.limit", equalTo(20))
		.body("data.gender", everyItem(equalTo("Female")))
		.body("result", not(emptyArray()));
}
	

	@Then("API should return all the female users with status as active")
	public void api_should_return_all_the_female_users_with_status_as_active() {
		resp.then().assertThat().statusCode(200)
		.body("code", equalTo(200))
		.body("meta.pagination.limit", equalTo(20))
		.body("data.gender", everyItem(equalTo("Female")))
		.body("data.status", everyItem(equalTo("Active")))
		.body("result", not(emptyArray()));
}
	}
	
