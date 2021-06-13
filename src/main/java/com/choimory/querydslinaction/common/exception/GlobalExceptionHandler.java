package com.choimory.querydslinaction.common.exception;

import com.choimory.querydslinaction.common.exception.util.CustomException;
import com.choimory.querydslinaction.common.response.code.CommonResponseCode;
import com.choimory.querydslinaction.common.response.util.CommonResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.choimory")
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<CommonResponseData> handlingRuntimeException(Exception e){
        return new ResponseEntity<>(CommonResponseData.builder()
                                                        .responseCode(CommonResponseCode.INTERNAL_SERVER_ERROR.getCode())
                                                        .responseMessage(CommonResponseCode.INTERNAL_SERVER_ERROR.getMessage())
                                                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CommonResponseData> handlingCustomException(CustomException e){
        return new ResponseEntity<>(CommonResponseData.builder()
                                                        .responseCode(e.getCode())
                                                        .responseMessage(e.getMessage())
                                                        .build(), HttpStatus.OK);
    }
}
