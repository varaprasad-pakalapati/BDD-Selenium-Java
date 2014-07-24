package com.app.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "html:output" }, features = { "src/main/resources/" }, glue = {
		"com.app.tests", "com.app.framework" })
public class Runner {

}