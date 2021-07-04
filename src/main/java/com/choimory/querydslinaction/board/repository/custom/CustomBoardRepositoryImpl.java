package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.board.repository.custom.expressions.BoardBooleanExpressions;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import static com.choimory.querydslinaction.board.entity.QBoard.board;
import static com.choimory.querydslinaction.boardComment.entity.QBoardComment.boardComment;
import static com.choimory.querydslinaction.user.entity.QUser.user;

@Repository
@AllArgsConstructor
public class CustomBoardRepositoryImpl implements CustomBoardRepository{
    private final JPAQueryFactory query;

    @Override
    public Page<Board> getBoardsDynamically(BoardRequestDto param, Pageable pageable) {
        QueryResults<Board> result = query.select(board)
                .from(board)
                .where(BoardBooleanExpressions.startsWithTitle(param.getTitle())
                        , BoardBooleanExpressions.containContent(param.getContent())
                        , BoardBooleanExpressions.orCategories(param.getCategory())
                        , BoardBooleanExpressions.eqNickname(param.getNickname())
                        , BoardBooleanExpressions.betweenView(param.getViewFrom(), param.getViewTo()))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
