package com.app.framework;

public class Logger {
	
	private static Status runStatus_;
	private static String scenarioName_;
	private static LogFile logFile = new LogFile();
	
	public static Status getRunStatus()
	{
		return runStatus_;
	}
	
	public static void setScenarioName(String scenarioName) {
		Logger.scenarioName_ = scenarioName;
	}
	
	public static String getScenarioName() {
		return scenarioName_;
	}
	
	private static void updateRunStatus(Status status) {
			runStatus_ = status;
	}
	
	public static void reportNote(String description, Status status)
    {
        logFile.writeAcceptanceStep(description, status);
        updateRunStatus(status);
    }

    public static void reportEvent(String expected, String actual, Status status)
    {
        logFile.writeActivityLog(expected, actual, status, "");
        updateRunStatus(status);
    }

    public static void reportEvent(String expected, String actual, Status status, String screenshotPath)
    {
        logFile.writeActivityLog(expected, actual, status, screenshotPath);
        updateRunStatus(status);
    }

    public static void reportSummaryDetails(String key, String keyValue)
    {
        logFile.writeSummaryDetails(key, keyValue);
    }

}
