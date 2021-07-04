package com.choimory.querydslinaction.boardComment.mapper;

import com.choimory.querydslinaction.boardComment.dto.response.BoardCommentResponseDto;
import com.choimory.querydslinaction.boardComment.entity.BoardComment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface BoardCommentMapper {
    BoardCommentResponseDto toDto(BoardComment entitiy);
    List<BoardCommentResponseDto> toDtos(List<BoardComment> entities);
}
