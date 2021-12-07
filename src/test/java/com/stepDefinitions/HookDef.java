package com.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;

import com.baseclass.BaseLibs;
import com.utilclass.ExtentReportUtils;
import com.utilclass.ScreenShotUtils;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class HookDef {

	public static WebDriver driver;

	@Before
	public void beforeSetup(Scenario sc) {
		ExtentReportUtils.createNodeReport(sc.getName());
		driver = BaseLibs.lanunchBrowser();
	}

	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}

	@After
	public void endReport(Scenario sc) {
		if (sc.isFailed()) {
			ExtentReportUtils.addComment(sc.getStatus().name(),
					sc.getName() + " scenario is " + sc.getStatus().name() + ExtentReportUtils.logger
							.addScreenCapture(ScreenShotUtils.takeScreenShot(driver, sc.getName()).getAbsolutePath()));
		}
		ExtentReportUtils.endTest();
	}
}
