package com.app.framework;

import java.util.Calendar;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverActionListeners extends AbstractWebDriverEventListener {

	private WebDriver driver;
	WebDriverExtensions driverExtensions;
	
	public WebDriverActionListeners() {
		Logger.reportSummaryDetails("start time", Calendar.getInstance().getTime().toString());
        Logger.reportSummaryDetails("application name", Config.getInstance().getApplicationName().toUpperCase());
        Logger.reportSummaryDetails("environment", Config.getInstance().getEnvironment().toUpperCase());
        Logger.reportSummaryDetails("host name", "LOCALHOST");
        Logger.reportSummaryDetails("execution type", Config.getInstance().getExecutionType().toUpperCase());
	}
		
	public WebDriverActionListeners(WebDriver driver) {
		this.driver = driver;
		driverExtensions = new WebDriverExtensions(this.driver);
	}
	
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("after change value of");
		super.afterChangeValueOf(element, driver);
		driverExtensions.sync();
        String expectedDescription = "Set the field '" + element + "' value";
        String currentValue;
        try
        {
            currentValue = element.getAttribute("value");
        }
        catch (Exception e)
        {
            currentValue = "- Perhaps element clicked.";
        }
        
        String actualDescription = "Successfully updated the field '" + element + "' value to '" + currentValue;
        Logger.reportEvent(expectedDescription, actualDescription, Status.Pass);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("after click on");
		super.afterClickOn(element, driver);
		String expectedDescription = "Click on the element '"+element +"'";
        String actualDescription = "Successfully clicked on the element '" +  element + "'";
        Logger.reportEvent(expectedDescription, actualDescription, Status.Pass);
		driverExtensions.sync();
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("after naviaged to");
		super.afterNavigateTo(url, driver);
		String expectedDescription = "Navigate to the URL + '" + url + "'";
        String actualDescription = "Successfully navigated to the URL: '" + url + "'";
        Logger.reportEvent(expectedDescription, actualDescription, Status.Pass);
		driverExtensions.sync();
	}
	
	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("after navigate back");
		driverExtensions.sync();
	}
	
	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("after navigate back");
		driverExtensions.sync();
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		System.out.println("before change value of");
		
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		System.out.println("before click on");
		
	}

	@Override
	public void onException(Throwable exception, WebDriver driver) {
		System.out.println("on exception");
		 Assert.fail();
		
	}
}
