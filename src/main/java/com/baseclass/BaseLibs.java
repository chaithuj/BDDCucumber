package com.baseclass;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.utilclass.WaitLib;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLibs {

	public static WebDriver driver;
	public static String brName;
	public static String env;
	public static String apiDataPath = "./src/test/resources/TestData/API/";
	public static String uIDataPath = "./src/test/resources/TestData/UI/";

	public static WebDriver lanunchBrowser() {
		if (env.equalsIgnoreCase("local")) {
			switch (brName.toUpperCase()) {
			case "CHROME":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--user-agent=Adsbot-Google");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("chrome://settings/clearBrowserData");
				driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
				break;
			case "EDGE":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			}
		} else if (env.equalsIgnoreCase("remote")) {
			switch (brName.toUpperCase()) {
			case "CHROME":
				DesiredCapabilities caps = new DesiredCapabilities().chrome();
				caps.setBrowserName("chrome");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption(ChromeOptions.CAPABILITY, options);
				caps.setPlatform(Platform.WINDOWS);
				caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				caps.setCapability("acceptInsecureCerts", false);
				caps.setCapability("acceptSslCerts", true);
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				WebDriverManager.chromedriver().setup();
				try {
					driver = new RemoteWebDriver(new URL("http://10.244.127.29:444/wb/hub"), caps);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			case "EDGE":
				break;
			}
		} else if (env.equalsIgnoreCase("saucelab")) {
			String URL = "https://user:accessKey@saucelabs.com:443//wb/hub";
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("parentTunnel", "name");
			caps.setCapability("tunnelIdentifier", "name");
			caps.setCapability("name", "name");
			caps.setCapability("platform", "Windows 10");
			caps.setCapability("browserVersion", "chrome");
			caps.setCapability("version", "95.0");
			caps.setCapability("build", "name");
			caps.setCapability("screenResolution", "1280X1024");
			try {
				driver = new RemoteWebDriver(new URL(URL), caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		WaitLib.iwait(driver, 30);
		return driver;
	}
}
