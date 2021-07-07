package com.choimory.querydslinaction.board.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class BoardRequestDto {
    private final Long idx;
    private final String title;
    private final String content;
    private final String nickname;
    private final List<String> category;
    private final String viewFrom;
    private final String viewTo;
    private final LocalDateTime registDateTimeFrom;
    private final LocalDateTime registDateTimeTo;
}
