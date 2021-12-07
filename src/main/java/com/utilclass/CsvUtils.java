package com.utilclass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import com.baseclass.BaseLibs;

import org.supercsv.io.CsvMapReader;

public class CsvUtils {

	static Map<String, Map<String, String>> allValues = new HashMap<String, Map<String, String>>();

	public static Map<String, String> getUiTestData(String Identifier, String testDataFilePath) {
		Map<String, Map<String, String>> csvTestCases = readTestDataValues(testDataFilePath);
		Map<String, String> data = csvTestCases.get(Identifier);
		return data;
	}

	public static Map<String, Map<String, String>> readTestDataValues(String testDataFilePath) {
		ICsvMapReader listReader;
		try {
			listReader = new CsvMapReader(new FileReader(testDataFilePath), CsvPreference.STANDARD_PREFERENCE);
			final String[] headers = listReader.getHeader(true);
			Map<String, String> fiedsInCurrentRow;

			while ((fiedsInCurrentRow = listReader.read(headers)) != null) {
				CsvUtils.readingHeaders(fiedsInCurrentRow);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allValues;
	}

	public static void readingHeaders(Map<String, String> header) {
		String testCase = "";
		if (header.keySet() != null) {
			Set<String> keys = header.keySet();
			for (String key : keys) {
				if (key.contains("Identifier")) {
					testCase = header.get(key);
					allValues.put(testCase, header);
					break;
				}

			}
		}
	}

	public String getApiDataFilePath(String fileName) throws FileNotFoundException {
		return Paths.get(BaseLibs.apiDataPath + fileName + ".csv").toString();
	}

	public String getUiDatafilePath(String fileName) throws FileNotFoundException {
		return Paths.get(BaseLibs.uIDataPath + fileName + ".csv").toString();
	}

}
