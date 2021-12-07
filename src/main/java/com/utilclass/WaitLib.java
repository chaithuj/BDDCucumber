package com.utilclass;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitLib {

	public static void waitTillClickable(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(findElement(by, driver)));
	}

	public static WebElement handleStaleElement(WebDriver driver, WebElement locator, int retryCount,
			int dealyInseconds) throws InterruptedException {
		WebElement element = null;
		while (retryCount >= 0) {
			try {
				element = locator;
				return element;
			} catch (StaleElementReferenceException e) {
				Thread.sleep(dealyInseconds);
				retryCount--;
			}
		}
		throw new StaleElementReferenceException("Element can't be recovered");
	}

	public static WebElement findElement(By locator, WebDriver driver) {
		try {
			WebElement eleent = driver.findElement(locator);
			return eleent;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public static void waitTillVisiblityOfFrameSwitchToIt(WebDriver driver, int timeOut, String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public static WebElement eWait(By by, WebDriver driver) {
		return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(findElement(by, driver)));
	}

	public static void iwait(WebDriver driver, int i) {
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
	}
}
