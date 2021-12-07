package com.stepDefinitions;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.utilclass.CsvUtils;
import com.utilclass.ExtentReportUtils;
import com.utilclass.GenericMethod;
import com.utilclass.HttpUtils;
import com.utilclass.LogReporting;
import com.utilclass.PropertyManger;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class api {

	static Logger log = LogReporting.getLogger(api.class);

	public static final Properties APIConfig = PropertyManger
			.getPropertyFromFile("./src/main/resources/properties/config.properties");

	@Given("when user hit API")
	public void whenUserHitAPI() {
		log.info("Start: whenUserHitAPI()");
		Map<String, String> header = new HashMap<>();
		Map<String, String> queryParam = new HashMap<>();
		// Setting headers
		header.put("Authorization", "test");
		header.put("Content-Type", "application/json");
		// Setting path param
		queryParam.put("test", "test");

		String apiurl = APIConfig.getProperty("test");
		String pathParam = APIConfig.getProperty("pathParm");
//		String requestBody = HttpUtils.readJsonFile("./src/main/resources/JsonPayload/test.json");

		String requestBody = HttpUtils.getJsonPayload("data", "./src/test/resources/TestData/API/test.csv",
				"./src/main/resources/JsonPayload/test.json");

//		requestBody = HttpUtils.updatePayload(requestBody, "authenticated", "true");
//		requestBody = HttpUtils.updatePayload(requestBody, "name.fullname", "chaithanya");

		Response response = HttpUtils.postRequest(apiurl, header, queryParam, pathParam, requestBody);
		System.out.println("status code " + response.getStatusCode());
		System.out.println("reponse " + response.getBody().asString());

		if (response.getStatusCode() == 200) {
			ExtentReportUtils.addComment("Passed", "status code 200");
			Assert.assertTrue(true);
		} else {
			ExtentReportUtils.addComment("Failed", "status code " + response.getStatusCode());
			Assert.assertTrue(false);
		}
	}

}
