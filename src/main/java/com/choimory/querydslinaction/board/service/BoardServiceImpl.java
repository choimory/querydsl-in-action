package com.choimory.querydslinaction.board.service;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public Page<BoardResponseDto> getBoards(BoardRequestDto param, Pageable pageable) {
        return null;
    }

    @Override
    public BoardResponseDto getBoard(BoardRequestDto param) {
        return null;
    }
}
