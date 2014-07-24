package com.app.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WebDriverExtensions {
	
	private WebDriver driver_;
	
	public WebDriverExtensions(WebDriver driver) {
		driver_ = driver;
	}
	
	public void sync() {
		
		String tempDriverHandle="";
        try
        {
            tempDriverHandle = driver_.getWindowHandle();
        }
        catch (Exception e)
        {
        	//TODO
        }

        WebDriverWait driverWait = new WebDriverWait(driver_, 5);
        driverWait.ignoring(IllegalStateException.class);
        driverWait.pollingEvery(500,TimeUnit.MILLISECONDS);
        driver_.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driverWait.until(new Function<WebDriver, Boolean>() {
        	public Boolean apply(WebDriver driver) {
        		try {
        			String browserStatus = (String)((JavascriptExecutor)driver).executeScript("return document.readyState");
        			return browserStatus.matches("complete");
        		}
        		catch(Exception e) {
        			return false;
        		}}
        	});
	}

}
