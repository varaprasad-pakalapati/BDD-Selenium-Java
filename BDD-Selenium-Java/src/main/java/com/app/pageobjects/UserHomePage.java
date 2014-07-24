package com.app.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.framework.InvalidPageObjectException;
import com.app.framework.Status;

public class UserHomePage {
	
	private WebDriver driver_;
	
	@FindBy(how = How.LINK_TEXT, using = "My Information")
	private WebElement myInformation_;

	public UserHomePage(WebDriver driver){
		driver_ = driver;
		PageFactory.initElements(driver_, this);
		if(!myInformation_.isDisplayed()) {
			throw new InvalidPageObjectException(driver_, "User home page is not loaded properly", Status.Fail);
		}
	}
}
