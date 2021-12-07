package com.utilclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManger {

	public static Properties getLocators(String fileName) {
		Properties property = new Properties();
		try {
			FileInputStream fileInputStream = null;
			fileInputStream = new FileInputStream("./src/main/resources/Locators/" + fileName + ".properties");
			property.load(fileInputStream);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return property;
	}

	public static Properties getPropertyFromFile(String fileName) {
		Properties property = new Properties();
		try {
			FileInputStream fileInputStream = null;
			fileInputStream = new FileInputStream(fileName);
			property.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return property;
	}

}
