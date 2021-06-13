package com.choimory.querydslinaction.common.response.util;

import com.choimory.querydslinaction.common.response.code.CommonResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseData <T> {
    @Builder.Default
    private final Integer responseCode = CommonResponseCode.OK.getCode();
    @Builder.Default
    private final String responseMessage = CommonResponseCode.OK.getMessage();
    private T data;
}
