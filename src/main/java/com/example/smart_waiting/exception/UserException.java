package com.example.smart_waiting.exception;

import com.example.smart_waiting.type.ErrorCode;
import lombok.*;

@Getter
@Setter
<<<<<<< HEAD
=======
@AllArgsConstructor
>>>>>>> 7a3644f (기존 Exception에서 ErrorCode enum class 추가 & 그를 활용한 UserException 생성)
@NoArgsConstructor
@Builder
public class UserException extends RuntimeException{
    private ErrorCode errorCode;
<<<<<<< HEAD

    public UserException(ErrorCode errorCode){
        super(errorCode.getDescription());
        this.errorCode = errorCode;
=======
    private String errorMessage;

    public UserException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
>>>>>>> 7a3644f (기존 Exception에서 ErrorCode enum class 추가 & 그를 활용한 UserException 생성)
    }
}
