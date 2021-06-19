package com.choimory.querydslinaction.board.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private final Long idx;
    private final String title;
    private final String content;
    private final String nickname;
}
