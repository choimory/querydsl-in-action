package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CustomBoardRepositoryImpl implements CustomBoardRepository{
    private final JPAQueryFactory query;

    @Override
    public Page<Board> getBoardsDynamically(BoardRequestDto param, Pageable pageable) {
        return null;
    }
}
