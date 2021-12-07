package com.runnerclass;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import com.baseclass.BaseLibs;
import com.stepDefinitions.HookDef;
import com.utilclass.ExtentReportUtils;

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(features = "./src/test/resources/FeatureFiles/", glue = { "com.stepDefinitions" }, tags = {
		"@testui1" }, plugin = { "json:target/cucumber-reports/CucumberTestReport.json",
				"html:target/report/cucumber-html-report" })

public class Runner {

	private TestNGCucumberRunner testNGCucumberRunner;
	// private WebDriver driver;
	public static RemoteWebDriver connection;

	@Parameters({ "browser", "env" })
	@BeforeClass(alwaysRun = true)
	public void setUpCucumber(String brname, String env) {
		ExtentReportUtils.startReport(brname);
		BaseLibs.brName = brname;
		BaseLibs.env = env;
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

	}

	@Test(dataProvider = "features")
	public void feature(PickleEventWrapper eventWrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
		testNGCucumberRunner.runScenario(eventWrapper.getPickleEvent());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
		HookDef.closeBrowser();
	}
	
	@AfterSuite
	public void endReport() {
		ExtentReportUtils.endReport();
	}
}