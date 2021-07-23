package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.board.repository.custom.expressions.BoardBooleanExpressions;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
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
                .orderBy(board.idx.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<Board> getOptionalColumnWithTuple(BoardRequestDto param, Pageable pageable) {
        List<Board> result = new ArrayList<>();
        List<Tuple> tuples = query.select(board.title
                , board.user)
                .from(board)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(board.idx.desc())
                .fetch();

        tuples.forEach(tuple -> {
            result.add(Board.builder()
                    .title(tuple.get(board.title))
                    .user(tuple.get(board.user))
                    .build());
        });

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Page<Board> getOptionalColumnWithProjectionFields(BoardRequestDto param, Pageable pageable) {
        QueryResults<Board> result = query.select(Projections.fields(Board.class
                , board.title
                , board.user))
                .from(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<Board> dynamicTotalCountWithJPAQuery(BoardRequestDto param, Pageable pageable) {
        JPAQuery<Board> buildedQuery = query.select(board)
                .from(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Board> result = buildedQuery.fetch();
        long count = param.getCachedTotalCount() == null ? buildedQuery.fetchCount() : param.getCachedTotalCount();

        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public Page<Board> joinWithTuple(BoardRequestDto param, Pageable pageable) {
        return null;
    }
}
