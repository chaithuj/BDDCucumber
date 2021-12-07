package com.utilclass;

import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.baseclass.BaseLibs;

public class GenericMethod {

	static WebDriver driver = BaseLibs.driver;
	static Logger log = LogReporting.getLogger(GenericMethod.class);

	public static WebDriver switchToWindow(String title) {
		WebDriver popup = null;
		try {
			Set<String> handles = driver.getWindowHandles();
			for (String s : handles) {
				String windowHandle = s;
				popup = driver.switchTo().window(windowHandle);
				if (popup.getTitle().equals(title)) {
					ExtentReportUtils.addComment("Passed", "User has switched to window " + title);
					return popup;
				}
			}
		} catch (Exception e) {
			log.error("Failed to switched to window ", e);
			ExtentReportUtils.addComment("Failed", "Failed to switched to window");
			System.out.println(e);
		}
		return popup;
	}

	public static void hitUrl(String url) {
		driver.get(url);
	}

	public String getTitle() {
		String title = null;
		try {
			title = driver.getTitle();
			ExtentReportUtils.addComment("Passed", "User fetch title" + title);
		} catch (Exception e) {
			log.error("Failed to fetech title", e);
			ExtentReportUtils.addComment("Failed", "Failed to fetch title");
			Assert.assertTrue(false);
		}
		return title;
	}

	public String getCurrentURL() {
		String currentURL = null;
		try {
			currentURL = driver.getCurrentUrl();
			ExtentReportUtils.addComment("Passed", "User fetch URL" + currentURL);
		} catch (Exception e) {
			log.error("Failed to fetech URL", e);
			ExtentReportUtils.addComment("Failed", "Failed to fetch URL");
			Assert.assertTrue(false);
		}
		return currentURL;
	}

	public Alert getAlert() {
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		try {
			getAlert().accept();
			ExtentReportUtils.addComment("Passed", "User clicked on accept alert");
		} catch (Exception e) {
			log.error("Failed to click on accept alert", e);
			ExtentReportUtils.addComment("Failed", "Failed to click on accept alert");
			Assert.assertTrue(false);
		}
	}

	public void dismissAlert() {
		try {
			getAlert().dismiss();
			ExtentReportUtils.addComment("Passed", "User clicked on dismiss alert");
		} catch (Exception e) {
			log.error("Failed to click on dismiss alert", e);
			ExtentReportUtils.addComment("Failed", "Failed to click on dismiss alert");
			Assert.assertTrue(false);
		}
	}

	public String getAlertText() {
		String text = null;
		try {
			text = getAlert().getText();
			ExtentReportUtils.addComment("Passed", "User fetched alert text" + text);
		} catch (Exception e) {
			log.error("Failed to fetch to fetch alert text", e);
			ExtentReportUtils.addComment("Failed", "Failed to fetched alert text");
			Assert.assertTrue(false);
		}
		return text;
	}

	public static void clickOnElement(By element, String fieldName) {
		try {
			WaitLib.waitTillClickable(element, driver);
			driver.findElement(element).click();
			ExtentReportUtils.addComment("Passed", "User clicked on" + fieldName);
		} catch (Exception e) {
			log.error("Failed to click opertaion " + element + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to click opertaion" + fieldName);
			Assert.assertTrue(false);
		}
	}

	public static void sendText(By element, String text, String fieldName) {
		try {
			WebElement webElement = WaitLib.findElement(element, driver);
			webElement.clear();
			webElement.sendKeys(text);
			ExtentReportUtils.addComment("Passed", "User has enterted text: " + text + " in " + fieldName);
		} catch (Exception e) {
			log.error("Failed to entert text: " + element + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to entert text: " + text + " in " + fieldName);
			Assert.assertTrue(false);
		}
	}

	public static boolean eleIsDisplayed(By element, String text, String fieldName) {
		boolean value = false;
		try {
			value = driver.findElement(element).isDisplayed();
			ExtentReportUtils.addComment("Passed", "User verifed " + fieldName + " is displayed");
		} catch (Exception e) {
			log.error("Failed to verify " + element + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to verify " + fieldName);
			Assert.assertTrue(false);
		}
		return value;
	}

	public static boolean isElementPresent(By element, String text, String fieldName) {
		List<WebElement> ele = driver.findElements(element);
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}

	public static void sendTextUsingActions(By element, String text, String fieldName) {
		try {
			new Actions(driver).moveToElement(WaitLib.findElement(element, driver)).click().sendKeys(text).build()
					.perform();
			ExtentReportUtils.addComment("Passed", "User has enterted text: " + text + " in " + fieldName);
		} catch (Exception e) {
			log.error("Failed to entert text: " + element + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to entert text: " + text + " in " + fieldName);
			Assert.assertTrue(false);
		}
	}

	public static void moveToElementUsingActions(By element, String text, String fieldName) {
		try {
			new Actions(driver).moveToElement(WaitLib.findElement(element, driver)).build().perform();
		} catch (Exception e) {
			log.error("Failed to move to element " + element + fieldName, e);
			Assert.assertTrue(false);
		}
	}

	public static void doubleClickonElement(By element, String text, String fieldName) {
		try {
			new Actions(driver).doubleClick(WaitLib.findElement(element, driver)).perform();
			ExtentReportUtils.addComment("Passed", "User double clicked on" + fieldName);
		} catch (Exception e) {
			log.error("Failed to double click on " + element + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to double click on " + fieldName);
			Assert.assertTrue(false);
		}
	}

	public static void selectByValue(By element, String option, String fieldName) {
		try {
			Select sel = new Select(WaitLib.findElement(element, driver));
			sel.selectByValue(option);
			ExtentReportUtils.addComment("Passed", "User select option " + option + " in " + fieldName);
		} catch (Exception e) {
			log.error("Failed to select option " + option + " in " + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to select option " + option + " in " + fieldName);
			Assert.assertTrue(false);
		}
	}

	public static String getText(By element, String fieldName) {
		String text = null;
		try {
			WebElement webElement = WaitLib.findElement(element, driver);
			text = webElement.getText();
			ExtentReportUtils.addComment("Passed", "User fetch text: " + text + " in " + fieldName);
		} catch (Exception e) {
			log.error("Failed to fetch text: " + element + fieldName, e);
			ExtentReportUtils.addComment("Failed", "Failed to fetch text: " + text + " in " + fieldName);
			Assert.assertTrue(false);
		}
		return text;
	}

	public void scrollElementView(By element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argumentss[0].scrollIntoView", WaitLib.findElement(element, driver));
	}

}
