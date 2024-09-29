package tests;

import Assertions.conditions;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.pet.Pet;
import models.pet.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.PetService;
import utils.DataGenerator;

public class PetNegativeTest {
    private static PetService petService;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
        petService = new PetService();
    }

//    @Test
//    void createPet() {
//
//    }

    @Test
    void getNonExcistentId() {
        petService.getPetById(-50)
                .should(conditions.statusCode(404));
    }

//    @Test
//    void getNonExcistentStatus() {
//        petService.getPetByStatus(Status.sold)
//                .should(conditions.statusCode(400));
//    }

//    @Test
//    void putPetNotFound() {
//        Pet pet = DataGenerator.generateFullDataPet();
//        petService.updatePet(pet)
//                .should(conditions.statusCode(404));
//    }

    @Test
    void deletePetNotFound() {
        petService.deletePet(DataGenerator.generateFullDataPet().getId())
                .should(conditions.statusCode(404));
    }


}
