package Assertions;

import Assertions.Conditions.StatusCondition;
import Assertions.Conditions.StatusCodeCondition;

public class Conditions {
    public static StatusCondition statusCondition(int code, String message) {
        return new StatusCondition(code);
    }

    public static StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }
}
