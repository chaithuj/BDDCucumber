package com.utilclass;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogReporting {

	private static boolean root = false;

	private LogReporting() {
		throw new AssertionError(this.getClass().getName());
	}

	public static Logger getLogger(@SuppressWarnings("rawtypes") Class cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("./src/main/resources/Properties/log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}
}
