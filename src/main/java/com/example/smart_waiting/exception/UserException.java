package com.example.smart_waiting.exception;

import com.example.smart_waiting.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;

    public UserException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
