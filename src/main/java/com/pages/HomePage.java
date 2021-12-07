package com.pages;

import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.By;
import com.baseclass.BaseLibs;
import com.utilclass.CsvUtils;
import com.utilclass.ExcelUtils;
import com.utilclass.GenericMethod;
import com.utilclass.PropertyManger;
import com.utilclass.WaitLib;

public class HomePage {

	static Properties homepageLocator = PropertyManger.getLocators("homePage");
	public static Map<String, String> csvValues;
	private static Map<String, String> excelValues;

	public static void clickOnAnthemButton() {
		WaitLib.iwait(BaseLibs.driver,30);
		GenericMethod.clickOnElement(By.xpath(homepageLocator.getProperty("anthemButton")),"anthum button");
		GenericMethod.switchToWindow("Learn to Code — For Free — Coding Courses for Busy People");
	}

	public static void hitURL(String url) {
		csvValues = CsvUtils.getUiTestData("url", "./src/test/resources/TestData/UI/test1.csv");
		GenericMethod.hitUrl(csvValues.get("dataURL"));
	}

	public static void clickOnMember() {
		GenericMethod.clickOnElement(By.xpath(homepageLocator.getProperty("memberButton")), "member button");
	}

	public static void hitURLFromExcel(String url) {
		excelValues = ExcelUtils.getValue("./src/test/resources/TestData/UI/test.xlsx", "test", 0, "KeyName", "col");
		System.out.println(excelValues.get("data1"));
		System.out.println(excelValues.get("url"));
		GenericMethod.hitUrl(excelValues.get("url"));
	}

}
