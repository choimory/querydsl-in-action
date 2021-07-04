package com.choimory.querydslinaction.board.mapper;

import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.entity.Board;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface BoardMapper {
    BoardResponseDto toDto (Board entity);
    List<BoardResponseDto> toDtos (List<Board> entities);
}
