package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.board.repository.custom.expressions.BoardBooleanExpressions;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
                        , BoardBooleanExpressions.betweenView(param.getViewFrom(), param.getViewTo())
                        , BoardBooleanExpressions.betweenRegistedDateTime(param.getRegistDateTimeFrom(), param.getRegistDateTimeTo()))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<Board> getBoardsOptionalColumnByTuple(BoardRequestDto param, Pageable pageable) {
        List<Board> result = new ArrayList<>();
        List<Tuple> tuples = query.select(board.title
                , board.user)
                .from(board)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        tuples.stream().forEach(tuple -> {
            result.add(Board.builder()
                    .title(tuple.get(board.title))
                    .user(tuple.get(board.user))
                    .build());
        });

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Board> getBoardsOptionalColumnByProjection(BoardRequestDto param, Pageable pageable) {
        return null;
    }
}
