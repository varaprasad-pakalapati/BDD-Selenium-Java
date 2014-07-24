package com.app.framework;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class LogFile {
	
	public static Path reportFilePath;
    private Path logFilePath;
	
    public LogFile() {
    	
		String timeStamp = Utilities.getDateTime();
		
		Path parentResultFolderName = Paths.get(System.getProperty("user.dir") + "\\Reports");
		String pathName = parentResultFolderName + "\\" + Config.getInstance().getApplicationName();
		String subResultsFolderName = pathName + "_" + timeStamp;
		
		Path orgLogFilePath = Paths.get(parentResultFolderName + "\\Prototype_Log.html");
		Path orgReportFilePath = Paths.get(parentResultFolderName + "\\Prototype_Report.html");
		
		boolean directory = new File(subResultsFolderName).mkdirs();
		if(directory) {
			logFilePath = Paths.get(subResultsFolderName + "\\LogReport.html");
		    reportFilePath = Paths.get(subResultsFolderName + "\\SummaryReport.html");
		}
		else {
			Logger.reportNote("Unable to create results folder.", Status.Fail);
		}
	    
	    try {
			List<String> reportContent = Files.readAllLines(orgReportFilePath, Charset.defaultCharset());
			List<String> logContent = Files.readAllLines(orgLogFilePath, Charset.defaultCharset());
			Files.write(reportFilePath, reportContent, Charset.defaultCharset());
			Files.write(logFilePath, logContent, Charset.defaultCharset());
	    }
	    catch(IOException e) {
	    	System.out.println("Report files not found");
	    }
    }
    
    public void writeScenarioName(String scenarioName) {
    	String replaceText = "createSummaryTestNode(\"" + scenarioName + "\");";
        updateFile(reportFilePath, "//Insert Calls", replaceText);
        updateFile(logFilePath, "//Insert Calls", replaceText);
    }
    
	public void writeAcceptanceStep(String acceptanceDescription, Status status) {
		String replaceReportText = "createAcceptanceTestNode(\"" + acceptanceDescription + "\",\"" + status.toString() + "\");";
        String replaceLogText = "createAcceptanceTestNode(\"" + "Acceptance Test:\"" + ",\"" + acceptanceDescription + "\",\"" + status.toString() + "\");";
        updateFile(reportFilePath, "//Insert Calls", replaceReportText);
        updateFile(logFilePath, "//Insert Calls", replaceLogText);
		
	}

	public void writeActivityLog(String expectedDescription, String actualDescription, Status status,
			String string) {
		String replaceText = "createAcceptanceTestNode(\"" + expectedDescription + "\",\"" + actualDescription + "\",\"" + status.toString() + "\");";
        updateFile(logFilePath, "//Insert Calls", replaceText);

        if (status == Status.Fail)
        {
            String replaceReportText = "createAcceptanceTestNode(\"" + actualDescription + "\",\"" + status.toString() + "\");";
            updateFile(reportFilePath, "//Insert Calls", replaceReportText);
        }
		
	}

	public void writeSummaryDetails(String key, String keyValue) {
		String replaceText = "setExecutionSummaryDetails(\"" + key + "\",\"" + keyValue + "\");";
        updateFile(reportFilePath, "//Insert Calls", replaceText);
		
	}
	
	private void updateFile(Path filePath, String findText, String replaceText){
		List<String> allContent = null;
		List<String> newContent = new LinkedList<String>();
		
		try {
			allContent = Files.readAllLines(filePath,Charset.defaultCharset());
		}
		catch(IOException e){
			System.out.println("Unable to read the lines from file - " + filePath + ". Error message is: " + e.getMessage());
		}
		
		for(String line : allContent)
		{
			if(line.contains(findText))
			{
				line = line.replace(findText, replaceText + "\n" + findText);
			}
			newContent.add(line);
		}
		
		try {
			Files.write(filePath, newContent, Charset.defaultCharset());
		} catch (IOException e) {
			System.out.println("Unable to write the lines to the file - " + filePath + ". Error message is: " + e.getMessage());
		}
	}

}
