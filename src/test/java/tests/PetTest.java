package tests;

import Assertions.Conditions;
import models.info.ApiResponse;
import models.pet.Pet;
import models.pet.Status;
import org.junit.jupiter.api.Assertions;
import service.PetService;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DataGenerator;

import java.io.File;


public class PetTest {
    private static PetService petService;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        petService = new PetService();
    }

    @Test
    void createPet() {
        Pet pet = DataGenerator.generateFullDataPet();

        petService.createPet(pet)
                .should(Conditions.statusCode(200));
    }

    @Test
    void getPetById() {
        Pet pet = DataGenerator.generateFullDataPet();

        int petId = petService.createPet(pet)
                .should(Conditions.statusCode(200))
                .as(Pet.class).getId();

        petService.getPetById(petId)
                .should(Conditions.statusCode(200));

    }

    @Test
    void deletePet() {
        Pet pet = DataGenerator.generateFullDataPet();

        int petId = petService.createPet(pet)
                .should(Conditions.statusCode(200))
                .as(Pet.class).getId();

        ApiResponse info = petService.deletePet(petId)
                .as(ApiResponse.class);

        Assertions.assertEquals(200, info.getCode());
    }

    @Test
    void updatePetNameStatus() {
        Pet pet = DataGenerator.generateFullDataPet();
        int petId = petService.createPet(pet)
                .should(Conditions.statusCode(200))
                .as(Pet.class).getId();

        ApiResponse info = petService.updatePetNameStatus(petId,"asdasd", Status.sold)
                .should(Conditions.statusCode(200))
                .as(ApiResponse.class);

        Assertions.assertEquals(200, info.getCode());

    }

    @Test
    void uploadPetImage(){
        Pet pet = DataGenerator.generateFullDataPet();
        File file = new File("src/test/resources/Chainsaw-Man.jpg");

        int petId = petService.createPet(pet)
                .should(Conditions.statusCode(200))
                .as(Pet.class).getId();

        petService.uploadImage(petId, file)
                .should(Conditions.statusCode(200));
    }
}
