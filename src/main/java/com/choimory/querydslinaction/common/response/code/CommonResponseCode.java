package com.choimory.querydslinaction.common.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCode {
    OK (200, "성공");

    private final Integer code;
    private final String message;
}
