package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBoardRepository {
    Page<Board> getBoardsDynamically(BoardRequestDto param, Pageable pageable);
    Page<Board> getBoardsOptionalColumnByTuple(BoardRequestDto param, Pageable pageable);
    Page<Board> getBoardsOptionalColumnByProjectionsFields(BoardRequestDto param, Pageable pageable);
    Page<BoardResponseDto> getBoardsOptionalColumnByProjectionsBean(BoardRequestDto param, Pageable pageable);
    Page<Board> getBoardsWithDynamicTotalCount(BoardRequestDto param, Pageable pageable);
}
