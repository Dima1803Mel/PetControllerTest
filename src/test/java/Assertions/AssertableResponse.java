package Assertions;

import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssertableResponse {

    public final ValidatableResponse response;

    public <T> T as(Class<T> tClass) {
        return response.extract().body().as(tClass);
    }

    public AssertableResponse should(Condition condition) {
        condition.check(response);
        return this;
    }
}
