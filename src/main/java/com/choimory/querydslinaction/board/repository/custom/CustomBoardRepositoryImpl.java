package com.choimory.querydslinaction.board.repository.custom;

import com.choimory.querydslinaction.board.dto.request.BoardRequestDto;
import com.choimory.querydslinaction.board.dto.response.BoardResponseDto;
import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.board.repository.custom.expressions.BoardBooleanExpressions;
import com.choimory.querydslinaction.common.exception.util.CustomException;
import com.choimory.querydslinaction.common.response.code.CommonResponseCode;
import com.choimory.querydslinaction.user.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.choimory.querydslinaction.board.entity.QBoard.board;
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
    public Page<BoardResponseDto> selectSubQuery(BoardRequestDto param, Pageable pageable) {
        QueryResults<BoardResponseDto> result = query.select(Projections.fields(BoardResponseDto.class
                                                                                , board.title
                                                                                , ExpressionUtils.as(JPAExpressions.select(user.email)
                                                                                                                    .from(user)
                                                                                                                    .where(user.nickname.eq(board.user.nickname)),"subQuery")))
                                .from(board)
                                .where(BoardBooleanExpressions.eqNickname(param.getNickname()))
                                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<Board> whereSubQuery(BoardRequestDto param, Pageable pageable) {
        QueryResults<Board> results = query.select(Projections.fields(Board.class
                                                                        , board.title
                                                                        , board.user))
                                            .from(board)
                                            .where(board.user.idx.eq(JPAExpressions.select(user.idx)
                                                                                    .from(user)
                                                                                    .where(user.nickname.eq(param.getNickname()))))
                                            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Board> getBoardsLikeTitleOrLikeContent(BoardRequestDto param, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (param == null){
          throw new CustomException(CommonResponseCode.BAD_REQUEST.getCode(), CommonResponseCode.BAD_REQUEST.getMessage());
        } else if(StringUtils.hasText(param.getTitle()) && StringUtils.hasText(param.getContent())){
            builder.and(BoardBooleanExpressions.startsWithTitle(param.getTitle())).or(BoardBooleanExpressions.containContent(param.getContent()));
        } else if(StringUtils.hasText(param.getTitle()) && !StringUtils.hasText(param.getContent())){
            builder.and(BoardBooleanExpressions.startsWithTitle(param.getTitle()));
        } else if (!StringUtils.hasText(param.getTitle()) && StringUtils.hasText(param.getContent())){
            builder.and(BoardBooleanExpressions.containContent(param.getContent()));
        } else {
            throw new CustomException(CommonResponseCode.BAD_REQUEST.getCode(), CommonResponseCode.BAD_REQUEST.getMessage());
        }

        QueryResults<Board> qr =  query.select(board)
                .from(board)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(qr.getResults(), pageable, qr.getTotal());
    }
}
