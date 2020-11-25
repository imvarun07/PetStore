package apiTests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import utility.BusinessFunctions;
import utility.CreatePayloadAndExecute;
import utility.PetStatus;

public class PetInfo extends BaseClass {

	@Test(enabled=true)
	public void addNewPet() {	
		CreatePayloadAndExecute payloadBuilder=new CreatePayloadAndExecute();
		BusinessFunctions businessFunctions = new BusinessFunctions();
		Response response = payloadBuilder.addNewPetToStore("petCategory","MyPet","petTag",PetStatus.AVAILABLE.getstatus());
		logger.info("Response - "+response.asString());
		response = businessFunctions.getPetById(response.jsonPath().getString("id"));
		Assert.assertTrue(response.statusCode()==200, "Error adding a new pet to the store");	
		logger.pass("New Pet Created");
	}

	@Test(enabled=true)
	public void getAvailablePets() {	
		CreatePayloadAndExecute payloadBuilder=new CreatePayloadAndExecute();
		BusinessFunctions businessFunctions = new BusinessFunctions();
		Response response = payloadBuilder.addNewPetToStore("petCategory","MyPet","petTag",PetStatus.AVAILABLE.getstatus());
		logger.info("Response - "+response.asString());
		String id = response.jsonPath().getString("id");
		businessFunctions.isPetAvailable(id);
		logger.pass("Pet Created found in Available Pets List");
	}

	@Test(enabled=true)
	public void updatePetStatus() {
		CreatePayloadAndExecute payloadBuilder=new CreatePayloadAndExecute();
		Response response = payloadBuilder.addNewPetToStore("petCategory","MyPet","petTag",PetStatus.AVAILABLE.getstatus());
		logger.info("Response - "+response.asString());
		String id = response.jsonPath().getString("id");
		payloadBuilder.updatePetStatus(id,PetStatus.SOLD.getstatus());
		logger.pass("Pet Status Updated");
	}

	@Test(enabled=false)
	public void deletePet() {
		CreatePayloadAndExecute payloadBuilder=new CreatePayloadAndExecute();
		Response response = payloadBuilder.addNewPetToStore("petCategory","MyPet","petTag",PetStatus.AVAILABLE.getstatus());
		logger.info("Response - "+response.asString());
		String id = response.jsonPath().getString("id");
		payloadBuilder.deletePet(id);
		logger.pass("Pet Deleted");
	}		
	

}