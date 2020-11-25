package utility;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.response.Response;

public class BusinessFunctions {
	public void isPetAvailable(String id) {
		CreatePayloadAndExecute payloadBuilder = new CreatePayloadAndExecute();
		Response response = payloadBuilder.getPetsWithStatus(PetStatus.AVAILABLE.getstatus());
		JsonArray jsonArray =new JsonArray();
		jsonArray = response.as(JsonArray.class);
		int a=0;
		for (int i = 0; i < jsonArray.size(); i++) {

			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
			if(jsonObject.get("id").getAsString().equals(id)) {
				a=1;
				break;
			}
		}
		Assert.assertTrue(a==1,"Pet Created Not Found in list Of Available Pets");
	}

	public Response getPetById(String id) {
		ApiConfig apiConfig= new ApiConfig();
		Response response = apiConfig.executeApi("GET", new JSONObject(), DataProvider.getConfigData("getPetById")+"/"+id);
		return response;
	}
}
