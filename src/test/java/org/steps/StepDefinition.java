package org.steps;
import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.*;

public class StepDefinition extends BaseClass{
	
	@Given("user navigates to login page")
	public void user_navigates_to_login_page() {
		getDriver();
		launchWebSite("https://www.facebook.com/");
	}
	
	 @When("user enters username and password from the {string} and rownumber {string}")
	 public void  When_user_enters_username_and_password_from_the_DataSheet_and_rownumber_RowNumber(String username, String password){
		 driver.findElement(By.id("email")).sendKeys(username);
		 driver.findElement(By.id("pass")).sendKeys(password);
	 }
	 
	 @And("user clicks the login button")
	 public void user_clicks_the_login_button() {
		 driver.findElement(By.name("login")).click();
	 }
	 
	 @Then("user must to navigated to home page")
	 public void user_must_to_navigated_to_home_page() throws InterruptedException {
		 Thread.sleep(3000);
		 String title = driver.getTitle();
		 System.out.println("Title : "+title);
		 if(title.equals("Facebook")) {
			 Assert.assertTrue(true);
		 }
	 }
}
