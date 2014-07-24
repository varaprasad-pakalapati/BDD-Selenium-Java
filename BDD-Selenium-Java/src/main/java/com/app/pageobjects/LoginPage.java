package com.app.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.framework.InvalidPageObjectException;
import com.app.framework.Status;

public class LoginPage {
	
	private WebDriver driver_;
	
	@FindBy(how = How.CSS,using = "input[id*='_ViewBSO_Login_Login_tbName']")
	private WebElement userName_;
	
	@FindBy(how = How.CSS,using = "input[id*=_ViewBSO_Login_Login_tbPass]")
	private WebElement password_;
	
	@FindBy(how = How.CSS,using = "span[id*='_ViewBSO_Login_Login_lblLogin']")
	private WebElement loginButton_;
	
	@FindBy(how = How.XPATH,using = "//div[@id='panErrorsAlert']/div[2]")
	private WebElement errorMessagePanel_;
	
	public LoginPage(WebDriver driver){
		driver_ = driver;
		PageFactory.initElements(driver_, this);
		if(!userName_.isDisplayed())
		{
			throw new InvalidPageObjectException(driver_, "Login page is not loaded", Status.Fail);
		}
	}
	
	public UserHomePage 
	Login(String userName, String password){
		userName_.sendKeys(userName);
		password_.sendKeys(password);
		loginButton_.click();
		return new UserHomePage(driver_);
	}

	public boolean LoginAsInvalidUser(String userName, String password) {
		userName_.sendKeys(userName);
		password_.sendKeys(password);
		loginButton_.click();
		return errorMessagePanel_.getText().contains("We are unable to match your Username");
	}
}
