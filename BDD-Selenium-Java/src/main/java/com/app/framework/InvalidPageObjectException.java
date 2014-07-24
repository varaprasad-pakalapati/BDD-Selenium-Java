package com.app.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class InvalidPageObjectException extends WebDriverException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPageObjectException(WebDriver driver, String message, Status status){
		System.out.println("");
	}
}
