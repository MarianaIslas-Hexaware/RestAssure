package StepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//@RunWith(Cucumber.class)
	public class stepDefinitions {

	    @Given("^User is on NetBanking landing page$")
	    public void user_is_on_netbanking_landing_page() throws Throwable {
		      System.out.println("Navigated to URL");

	    }

	    //REgular expressions are for different data but same implementation
	    @When("^login into application with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	    public void login_into_application_with_username_something_and_password_something(String strArg1, String strArg2) throws Throwable {
	      System.out.println(strArg1 + " "+ strArg2);
	    }


	    @Then("^Home page is populated$")
	    public void home_page_is_populated() throws Throwable {
	        //homepage validation
	    }

	    @And("^Cards are displayed \"([^\"]*)\"$")
	    public void cards_are_displayed_something(String strArg1) throws Throwable {
	       //if true -- 
	    }
}
