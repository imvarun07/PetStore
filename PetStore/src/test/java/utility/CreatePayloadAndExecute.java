package utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import com.google.gson.JsonObject;

import apiTests.PetInfo;
import io.restassured.response.Response;

public class CreatePayloadAndExecute extends PetInfo {

	public Response addNewPetToStore(String categoryName, String petName, String tagName, String petStatus) {
		Map<String,Object> pet = new HashMap<String, Object>();
		pet.put("id",DataProvider.idGenerator());

		Map<String,Object> categoryInfo = new HashMap<String, Object>();
		categoryInfo.put("id", DataProvider.idGenerator());
		categoryInfo.put("name",categoryName);

		pet.put("category", categoryInfo);
		pet.put("name",petName);

		List<String> urls = new ArrayList<String>();
		urls.add("URLS");

		pet.put("photoUrls", urls);

		Map<String,Object> tagsInfo = new HashMap<String, Object>();
		tagsInfo.put("id", DataProvider.idGenerator());
		tagsInfo.put("name", tagName);
		List<Object> tagsList = new ArrayList<Object>();
		tagsList.add(tagsInfo);
		pet.put("tags",tagsList);

		pet.put("status",petStatus);

		ApiConfig apiconfig = new ApiConfig();
		Response response = apiconfig.executeApi("POST", new JSONObject(pet), DataProvider.getConfigData("addNewPet"));
		return response;		
	}

	public Response getPetsWithStatus(String petstatus) {
		ApiConfig apiconfig = new ApiConfig();
		Response response = apiconfig.executeApi("GET", new JSONObject(),  DataProvider.getConfigData("getPetByStatus")+"?status="+petstatus);
		return response;	
	}

	public void updatePetStatus(String petID, String petStatus) {
		JSONObject json = null;
		ApiConfig apiconfig = new ApiConfig();
		try {

			Response response = apiconfig.executeApi("GET", new JSONObject(),  DataProvider.getConfigData("getPetById")+"/"+petID);
			JSONParser parser = new JSONParser();  
			json = (JSONObject) parser.parse(response.asString());  
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		json.put("status", petStatus);
		Response response = apiconfig.executeApi("PUT", json,  DataProvider.getConfigData("addNewPet"));
		Assert.assertTrue(response.statusCode()==200, "Pet Status not Updated");
	}

	public void deletePet(String petID) {
		ApiConfig apiconfig = new ApiConfig();
		Response response = apiconfig.executeApi("DELETE", new JSONObject(),  DataProvider.getConfigData("deletePet")+"/"+petID);
		Assert.assertTrue(response.statusCode()==200, "Pet Not Deleted Successfully");	
	}
}
