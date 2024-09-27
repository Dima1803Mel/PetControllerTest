package service;

import Assertions.AssertableResponse;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import models.pet.Pet;
import models.pet.Status;

import java.io.File;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;

public class PetService {
    public AssertableResponse createPet(Pet pet) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .header("api_key", "12345")
                .body(pet)
                .post("")
                .then());
    }

    public AssertableResponse getPetById(int petId) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .header("api_key", "12345")
                .pathParams("petId", petId)
                .get("/{petId}")
                .then());
    }

    public AssertableResponse deletePet(int petId) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .header("api_key", "12345")
                .pathParams("petId", petId)
                .delete("/{petId}")
                .then());
    }

    public AssertableResponse updatePetNameStatus(int petId, String petName, Status status) {
        return new AssertableResponse(given().contentType(ContentType.URLENC)
                .header("api_key", "12345")
                .pathParams("petId", petId)
                .formParam("name", petName)
                .formParam("status", status)
                .post("/{petId}")
                .then());
    }

    public AssertableResponse updatePet(Pet pet) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put("")
                .then());
    }

    @SneakyThrows
    public AssertableResponse uploadImage(int petId, File file) {
        return new AssertableResponse(given()
                .contentType(ContentType.MULTIPART)
                .header("api_key", "12345")
                .pathParams("petId", petId)
                .multiPart("additionalMetadata", "sdfsdhhhh")
                .multiPart("file", "myFile", Files.readAllBytes(file.toPath()))
                .when()
                .post("/{petId}/uploadImage")
                .then());
    }
}
