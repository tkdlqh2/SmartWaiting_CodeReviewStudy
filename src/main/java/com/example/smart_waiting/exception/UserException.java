package com.example.smart_waiting.exception;

import com.example.smart_waiting.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserException extends RuntimeException{
    private ErrorCode errorCode;

    public UserException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
