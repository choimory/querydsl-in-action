package com.choimory.querydslinaction.board.dto.response;

import com.choimory.querydslinaction.boardComment.dto.response.BoardCommentResponseDto;
import com.choimory.querydslinaction.user.dto.response.UserResponseDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long idx;
    private String category;
    private String title;
    private String content;
    private UserResponseDto user;
    private Long upVote;
    private Long downVote;
    private List<BoardCommentResponseDto> boardComments;
}
