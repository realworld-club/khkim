package com.realworld.project.util.error;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class ErrorModel {

    ErrorModelNested errors;

    public ErrorModel generator(Exception e) {
        return new ErrorModel(ErrorModelNested.generator(e));
    }

    @Value
    private static class ErrorModelNested {
        List<String> body;

        private static ErrorModelNested generator(Exception e) {
            List<String> body = new ArrayList<>();
            body.add(e.getMessage());
            return new ErrorModelNested(body);
        }
    }
}
