package com.example.smart_waiting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult {

    private boolean success;
    private String errorCode;

    public static ServiceResult success(){
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    public static ServiceResult fail(String errorCode){
        return new ServiceResult(false,errorCode);
    }

}
