package com.choimory.querydslinaction.board.service;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.board.mapper.BoardMapper;
import com.choimory.querydslinaction.board.repository.BoardRepository;
import com.choimory.querydslinaction.common.exception.util.CustomException;
import com.choimory.querydslinaction.common.response.code.CommonResponseCode;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public Page<BoardResponseDto> getBoards(BoardRequestDto param, Pageable pageable) {
        Page<Board> boards = boardRepository.getBoardsDynamically(param, pageable);
        List<BoardResponseDto> dtos = boardMapper.toDtos(boards.getContent());
        return new PageImpl<>(dtos, boards.getPageable(), boards.getTotalElements());
    }

    @Override
    public BoardResponseDto getBoard(BoardRequestDto param) {
        return boardMapper.toDto(boardRepository.findById(param.getIdx())
                .orElseThrow(() -> new CustomException(CommonResponseCode.INTERNAL_SERVER_ERROR.getCode(), CommonResponseCode.INTERNAL_SERVER_ERROR.getMessage())));
    }

    @Override
    public Page<BoardResponseDto> getBoardTuple(BoardRequestDto param, Pageable pageable) {
        Page<Board> boards = boardRepository.getOptionalColumnWithTuple(param, pageable);
        return new PageImpl<>(boardMapper.toDtos(boards.getContent()), pageable, boards.getTotalElements());
    }
}
