package Assertions.Conditions;

import Assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import models.info.ApiResponse;
import org.junit.jupiter.api.Assertions;

@AllArgsConstructor
public class StatusCondition implements Condition {

    private final int expectedCode;

    @Override
    public void check(ValidatableResponse response) {
        ApiResponse code = response.extract().as(ApiResponse.class);
        Assertions.assertEquals(expectedCode, code.getCode());
    }
}
