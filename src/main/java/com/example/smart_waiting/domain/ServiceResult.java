package com.example.smart_waiting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceResult {

    private boolean success;
    private String message;

    ServiceResult(boolean success){
        this.success = success;
    }

    public static ServiceResult success(){

        return new ServiceResult(true);
    }

    public static ServiceResult fail(String message){
        return new ServiceResult(false,message);
    }
}
