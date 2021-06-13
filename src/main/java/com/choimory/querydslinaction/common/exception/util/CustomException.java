package com.choimory.querydslinaction.common.exception.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    private final Integer code;
    private final String message;
}
