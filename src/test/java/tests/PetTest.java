package tests;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;

public class PetTest {

    @Test
    public void getPetFindByStatus() {
        given()
                .when()
                .get()
                .then();
    }
}
