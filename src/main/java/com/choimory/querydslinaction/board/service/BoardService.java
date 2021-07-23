package com.choimory.querydslinaction.board.service;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<BoardResponseDto> getBoards(BoardRequestDto param, Pageable pageable);
    BoardResponseDto getBoard(BoardRequestDto param);
    Page<BoardResponseDto> getBoardTuple(BoardRequestDto param, Pageable pageable);
    Page<BoardResponseDto> getBoardFields(BoardRequestDto param, Pageable pageable);
}
