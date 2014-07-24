package com.app.framework;

import java.io.FileInputStream;
import java.util.Properties;

public class Config{
	
	private String browserName ;
	private Properties OR = null;
	private Properties CONFIG = null;
	private Properties ENV = null;
	private String url = null;
	static Config config;
//	Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	private String applicationName;
	private String executionType;
	private String environment;
	private String sendMail;

	private String distributionList;

	public Config()
	{
		if(OR == null)
		{
			//initialize OR
			try {
				ENV = new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\app\\config\\ENV.properties");
				ENV.load(fs);
				setBrowserName(ENV.getProperty("browserName"));
				setApplicationName(ENV.getProperty("applicationName"));
				setEnvironment(ENV.getProperty("testEnv"));
				setExecutionType(ENV.getProperty("executionType"));
				setSendMail(ENV.getProperty("sendMail"));
				setDistributionList(ENV.getProperty("distributionList"));
				
				OR = new Properties();
				fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\app\\config\\OR.properties");
				OR.load(fs);
				
				CONFIG = new Properties();
				fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\app\\config\\" + ENV.getProperty("testEnv") + "_config.properties");
				CONFIG.load(fs);
				setUrl(CONFIG.getProperty("appURL"));
				
				System.out.println(CONFIG.getProperty("appURL") + "****" + OR.getProperty("testEnv"));
			} catch (Exception e) {
				System.out.println("Error on intializing properties files");
			}
		}
	}
	
	private void setBrowserName(String browserName)
	{
		this.browserName = browserName;
	}
	
	public String getBrowserName() {
		return browserName;
	}
	
	private void setUrl(String url)
	{
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	private void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}
	
	public String getApplicationName() {
		return applicationName;
	}
	
	private void setExecutionType(String executionType)
	{
		this.executionType = executionType;
	}
	
	public String getExecutionType() {
		return executionType;
	}
	
	private void setEnvironment(String environment)
	{
		this.environment = environment;
	}
	
	public String getEnvironment() {
		return environment;
	}
	
	public String isSendMail() {
		return sendMail;
	}

	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}

	public String getDistributionList() {
		return distributionList;
	}

	public void setDistributionList(String distributionList) {
		this.distributionList = distributionList;
	}
	
	/******************Singleton behaviour*********/
	
	public static Config getInstance() {
		if(config == null)
			config = new Config();
		return config;
	}
	
	/******************Logging********/
	
	/*public void log(String msg) {
		APPLICATION_LOGS.debug(msg);
	}*/

}
