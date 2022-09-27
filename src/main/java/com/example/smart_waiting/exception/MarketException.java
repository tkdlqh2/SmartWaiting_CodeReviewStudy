package com.example.smart_waiting.exception;

import com.example.smart_waiting.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class MarketException extends RuntimeException{
    private ErrorCode errorCode;

    public MarketException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
}
