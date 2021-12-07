package com.utilclass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HttpUtils {

	static Response response = null;

	public static Response postRequest(String baseURI, Map<String, String> header, Map<String, String> queryParams,
			String pathParams, String requestPayload) {
		RestAssured.baseURI = baseURI;
		response = RestAssured.given().headers(header).queryParams(queryParams).body(requestPayload).log().all().when()
				.post(pathParams);
		return response;
	}

	public static Response postRequestFromInBody(String baseURI, Map<String, String> header,
			Map<String, String> queryParams, String pathParams, Map<String, String> requestPayload) {
		RestAssured.baseURI = baseURI;
		response = RestAssured.given()
				.config(RestAssured.config().encoderConfig(
						EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.formParams(requestPayload).headers(header).queryParams(queryParams).when().post(pathParams);
		return response;
	}

	public static Response getRequest(String baseURI, Map<String, String> header, Map<String, String> queryParams,
			String pathParams) {
		RestAssured.baseURI = baseURI;
		response = RestAssured.given().headers(header).queryParams(queryParams).when().get(pathParams);
		return response;
	}

	public static Response putRequest(String baseURI, Map<String, String> header, Map<String, String> queryParams,
			String pathParams, String requestPayload) {
		RestAssured.baseURI = baseURI;
		response = RestAssured.given().headers(header).queryParams(queryParams).body(requestPayload).log().all().when()
				.put(pathParams);
		return response;
	}

	public static Response deleteRequest(String baseURI, Map<String, String> header, Map<String, String> queryParams,
			String pathParams, String requestPayload) {
		RestAssured.baseURI = baseURI;
		response = RestAssured.given().headers(header).queryParams(queryParams).body(requestPayload).log().all().when()
				.delete(pathParams);
		return response;
	}

	public static String readJsonFile(String jsonPath) {
		String json = null;
		try {
			json = FileUtils.readFileToString(new File(jsonPath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String updatePayload(String json, String path, Object newValue) {
		DocumentContext ctx = JsonPath.parse(json);
		DocumentContext finalJson = ctx.set(path, newValue);
		return finalJson.jsonString();
	}

	public static String getJsonPayload(String Identifier, String testDataFilePath, String jsonTempelatePath) {
		Map<String, Map<String, String>> csvTestCases = new CsvUtils().readTestDataValues(testDataFilePath);
		Map<String, String> data = csvTestCases.get(Identifier);
		String json = readJsonFile(jsonTempelatePath);
		DocumentContext ctx = JsonPath.parse(json);
		data.forEach((k, v) -> {
			jsonRequest(ctx, k, v);
		});
		return ctx.jsonString();
	}

	private static DocumentContext jsonRequest(DocumentContext ctx, String k, String v) {
		String key = "$.".concat(k);
		if (v == null) {
			removeKey(ctx, key);
		} else if (("false".equalsIgnoreCase(v) || ("true").equalsIgnoreCase(v))) {
			replaceJsonBooleanValue(ctx, key, v);
		} else
			replaceJsonKeyValue(ctx, key, v);
		return ctx;
	}

	private static DocumentContext replaceJsonKeyValue(DocumentContext ctx, String path, String newValue) {
		return ctx.set(path, newValue);
	}

	private static DocumentContext replaceJsonBooleanValue(DocumentContext ctx, String path, String newValue) {
		if (("false").equalsIgnoreCase(newValue)) {
			return ctx.set(path, Boolean.FALSE);
		} else
			return ctx.set(path, Boolean.TRUE);
	}

	private static DocumentContext removeKey(DocumentContext ctx, String key) {
		return ctx.delete(key);
	}
}
