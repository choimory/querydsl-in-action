package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBoardRepository {
    Page<Board> getBoardsDynamically(BoardRequestDto param, Pageable pageable);
    Page<Board> getOptionalColumnWithTuple(BoardRequestDto param, Pageable pageable);
    Page<Board> getOptionalColumnWithProjectionFields(BoardRequestDto param, Pageable pageable);
    Page<Board> dynamicTotalCountWithJPAQuery(BoardRequestDto param, Pageable pageable);
    Page<BoardResponseDto> selectSubQuery(BoardRequestDto param, Pageable pageable);
    Page<Board> whereSubQuery(BoardRequestDto param, Pageable pageable);
    Page<Board> getBoardsLikeTitleOrLikeContent(BoardRequestDto param, Pageable pageable);
}
