package com.choimory.querydslinaction.boardComment.dto.response;

import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.user.dto.response.UserResponseDto;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentResponseDto {
    private Long idx;
    private BoardResponseDto board;
    private UserResponseDto user;
    private String comment;
}
