package com.utilclass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportUtils {

	public static ExtentReports extendReport;
	public static ExtentTest logger;

	public static void startReport(String browser) {
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY HH mm ss");
		String dateTostr = format.format(curDate);
		String executionStartDate = dateTostr.substring(0, 11);
		String executionStartTime = dateTostr.substring(12, 20).replace(" ", "-");
		extendReport = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport/"
				+ executionStartDate + "/report-" + executionStartTime + ".html", true);
		extendReport.addSystemInfo("HostName", "testing").addSystemInfo("browser", browser);
		extendReport.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
	}

	public static void createNodeReport(String name) {
		logger = extendReport.startTest(name);
	}

	public static void addComment(String status, String message) {
		switch (status.toUpperCase()) {
		case "PASSED":
			logger.log(LogStatus.PASS, message);
			break;
		case "FAILED":
		case "EXCEPTION":
			logger.log(LogStatus.FAIL, message);
			break;
		case "INFO":
			logger.log(LogStatus.INFO, message);
			break;
		case "ERROR":
			logger.log(LogStatus.ERROR, message);
			break;
		case "SKIPPED":
			logger.log(LogStatus.SKIP, message);
			break;
		default:
			logger.log(LogStatus.UNKNOWN, message);
			break;
		}
	}

	public static void endTest() {
		extendReport.endTest(logger);
	}

	public static void endReport() {
		extendReport.flush();
	}
}
