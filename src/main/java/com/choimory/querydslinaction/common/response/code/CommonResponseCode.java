package com.choimory.querydslinaction.common.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseCode {
    OK (200, "성공")
    , BAD_REQUEST(400, "잘못된 요청입니다")
    , NOT_FOUND(404, "경로를 찾을 수 없습니다")
    , INTERNAL_SERVER_ERROR(500, "서버 오류입니다");

    private final Integer code;
    private final String message;
}
