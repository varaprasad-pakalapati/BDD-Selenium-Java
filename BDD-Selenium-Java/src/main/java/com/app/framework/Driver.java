package com.app.framework;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Driver {
	
	public static EventFiringWebDriver driver;
	
	static Logger logger;
	Config config;
	WebDriverActionListeners invokeWde;
	
	private static boolean isInitial = true;
	private WebDriver driver_;
	private static String Url;
	private int totalPassedScenarios_ = 0;
	private int totalFailedScenarios_ = 0;
	private int totalNumberOfScenarios_ = 0;
	
	/*@BeforeClass
	public static void beforeClass() {
		System.out.println("Before Class");
	}*/
	
	@Before()
	public void beforeScenario(Scenario scenario)
	{
		//logger.setScenarioName(scenario.getName());
		setUrl();
		initializeDriver();
		driver = new EventFiringWebDriver(driver_);
		if(isInitial) {
			invokeWde = new WebDriverActionListeners(); // For invoking summary results. 
			isInitial = false;
		}
		WebDriverActionListeners wde = new WebDriverActionListeners(driver);
		driver.register(wde);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@After
	public void afterscenario(Scenario scenario)
	{
		System.out.println("***********");
		driver.close();
		System.out.println("***********");
		if (Logger.getRunStatus() == Status.Pass)
        {
            totalPassedScenarios_++;
            Assert.assertTrue("Scenario Passed",true);
        }
        else
        {
            totalFailedScenarios_++;
            Assert.fail("Please check through the execution report against scenario");
        }
		//System.out.println("Scenario status:" + scenario.getStatus());
		//scenario.embed(arg0, arg1);
	}
	
	/*@AfterClass
	public static void afterTest() {
		logger.ReportSummaryDetails("end time", Utilities.getDateTime());
        boolean sendMailFlag = Boolean.parseBoolean(config.getInstance().isSendMail());
       if(sendMailFlag)
       {
           String testDetails;
           if (totalFailedScenarios_ + totalPassedScenarios_ == totalNumberOfScenarios_ )
               testDetails = " - {Total Scripts: " + totalNumberOfScenarios_ + "| Passed: " +
                             totalPassedScenarios_ + "| Failed:" + totalFailedScenarios_ + "}";
           else
               testDetails = "";
            SendMail sendMail = new SendMail();
            sendMail.transport(testDetails, LogFile.reportFilePath.toString());
        }
	}*/
	
	private void initializeDriver() {
		
		switch (Config.getInstance().getBrowserName().toUpperCase()) {
		case ("FIREFOX"):
			driver_ = new FirefoxDriver();
			break;
		case ("CHROME"):
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chrome\\chromedriver.exe");
			driver_ = new ChromeDriver();
			break;
		case ("IE"):
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\ie\\iedriver.exe");
			driver_ = new InternetExplorerDriver();
			break;
		default:
			driver_ = new FirefoxDriver();
			break;
		}			
	}
	
	private void setUrl() {
		Url = Config.getInstance().getUrl();
	}

	public static String getUrl() {
		return Url;
	}
}
