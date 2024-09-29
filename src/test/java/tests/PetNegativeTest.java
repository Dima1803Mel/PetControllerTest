package tests;

import Assertions.conditions;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.PetService;

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
    void getById() {
        petService.getPetById(-50)
                .should(conditions.statusCode(404));
    }
}
