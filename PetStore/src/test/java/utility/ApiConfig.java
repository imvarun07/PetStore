package utility;


import org.json.simple.JSONObject;

import apiTests.BaseClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {
	
	public Response executeApi(String apiMethod,JSONObject json, String uri) {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json");
		request.body(json.toJSONString());
		Response response = null;
		
		
		switch (apiMethod) {
		case "POST":
			response = request.post(uri);
			System.out.println(response.asString());
			break;
			
		case "GET":
			response = request.get(uri);
			System.out.println(response.asString());
			break;
			
		case "PUT":
			response = request.put(uri);
			System.out.println(response.asString());
			break;
			
		case "DELETE":
			response = request.delete(uri);
			System.out.println(response.asString());
			break;

		default:
			break;
		}
		
		return response;
	}

}
