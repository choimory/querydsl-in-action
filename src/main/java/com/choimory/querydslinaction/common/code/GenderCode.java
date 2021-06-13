package com.choimory.querydslinaction.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderCode {
    MALE("남성")
    , FEMALE("여성");

    private final String value;
}
