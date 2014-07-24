package com.app.tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.app.framework.Driver;
import com.app.pageobjects.LoginPage;
import com.app.pageobjects.PageObjectInstances;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTestStepDefs extends PageObjectInstances {
	private WebDriver driver_;
	HashMap dictionary = new HashMap();

	public LoginTestStepDefs() {
		driver_ = Driver.driver;
	}

	@Given("^I go to businessOnline application$")
	public void I_go_to_BusinessOnline_application() {
		driver_.navigate().to(Driver.getUrl());
		System.out.println("Iam in background");
	}

	@When("^I login to application with invalid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_login_to_application_with_invalid_and(String userName,
			String password) {
		loginPage = new LoginPage(driver_);
		dictionary.put("loginResult",
				loginPage.LoginAsInvalidUser(userName, password));
	}

	@When("^I login to application with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_login_to_application_with_and(String userName, String password) {
		loginPage = new LoginPage(driver_);
		loginPage.Login(userName, password);
	}

	@Given("^Runmode is \"([^\"]*)\"$")
	public void Runmode_is(String runMode) {
		if (runMode.toUpperCase().equals("N"))
			throw new PendingException("Skipping test case due to runmode :"
					+ runMode);
	}

	@Then("^Login should be \"([^\"]*)\"$")
	public void Login_should_be(String expectedResult) {
		if (dictionary.containsKey("loginResult")) {
			if ((boolean) dictionary.get("loginResult"))
				System.out.println("Login is failed");
			else
				System.out.println("Login is success");
		}
	}

}
