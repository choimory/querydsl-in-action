package com.choimory.querydslinaction.board.service;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<BoardResponseDto> getBoards(final BoardRequestDto param, final Pageable pageable);
    BoardResponseDto getBoard(final BoardRequestDto param);
    Page<BoardResponseDto> getBoardTuple(final BoardRequestDto param, final Pageable pageable);
    Page<BoardResponseDto> getBoardFields(final BoardRequestDto param, final Pageable pageable);
    Page<BoardResponseDto> getBoardsWithDynamicTotalCount(BoardRequestDto param, final Pageable pageable);
    Page<BoardResponseDto> selectSubQuery(final BoardRequestDto param, final Pageable pageable);
    Page<BoardResponseDto> whereSubQuery(final BoardRequestDto param, final Pageable pageable);
}
