package com.app.framework;

import java.util.Calendar;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverActionListeners extends AbstractWebDriverEventListener {

	private WebDriver driver;
	WebDriverExtensions driverExtensions;
	Logger logger;
	Config config;
	
	public WebDriverActionListeners() {
		logger.reportSummaryDetails("start time", Calendar.getInstance().getTime().toString());
        logger.reportSummaryDetails("application name", config.getInstance().getApplicationName().toUpperCase());
        logger.reportSummaryDetails("environment", config.getInstance().getEnvironment().toUpperCase());
        logger.reportSummaryDetails("host name", "LOCALHOST");
        logger.reportSummaryDetails("execution type", config.getInstance().getExecutionType().toUpperCase());
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
        logger.reportEvent(expectedDescription, actualDescription, Status.Pass);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("after click on");
		super.afterClickOn(element, driver);
		String expectedDescription = "Click on the element '"+element +"'";
        String actualDescription = "Successfully clicked on the element '" +  element + "'";
        logger.reportEvent(expectedDescription, actualDescription, Status.Pass);
		driverExtensions.sync();
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("after naviaged to");
		super.afterNavigateTo(url, driver);
		String expectedDescription = "Navigate to the URL + '" + url + "'";
        String actualDescription = "Successfully navigated to the URL: '" + url + "'";
        logger.reportEvent(expectedDescription, actualDescription, Status.Pass);
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
