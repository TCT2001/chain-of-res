package com.example.pattern.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionResponse {
    enum ResponseCode {
        SUCCESS,
        ERROR,
        EXCEPTION
    }

    private ResponseCode responseCode;
    private String description;

    public static ActionResponse getSuccess() {
        return new ActionResponse(ResponseCode.SUCCESS, null);
    }
}
