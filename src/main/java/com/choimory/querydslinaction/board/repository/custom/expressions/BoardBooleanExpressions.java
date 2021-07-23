package com.choimory.querydslinaction.board.repository.custom.expressions;

import com.choimory.querydslinaction.user.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.tomcat.jni.Local;
import org.springframework.util.StringUtils;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import static com.choimory.querydslinaction.board.entity.QBoard.board;
import static com.choimory.querydslinaction.boardComment.entity.QBoardComment.boardComment;
import static com.choimory.querydslinaction.user.entity.QUser.user;

public class BoardBooleanExpressions {

    /**
     * 게시물 내용 %LIKE% 검색
     * @param content
     * @return
     */
    public static BooleanExpression containContent(String content){
        return StringUtils.hasText(content) ? board.content.contains(content) : null;
    }

    /**
     * 게시물 제목 LIKE% 검색
     * @param title
     * @return
     */
    public static BooleanExpression startsWithTitle(String title){
        return StringUtils.hasText(title) ? board.title.startsWithIgnoreCase(title) : null;
    }

    /**
     * 게시물 카테고리 IN 검색
     * @param categories
     * @return
     */
    public static BooleanExpression inCateogry(List<String> categories){
        return categories == null || categories.isEmpty() ? null : board.category.in(categories);
    }

    /**
     * 게시물 카테고리 Loop OR 검색
     * @param categories
     * @return
     */
    public static BooleanExpression orCategories(List<String> categories){
        BooleanExpression booleanExpression = null;

        if(categories != null) {
            for (String category : categories) {
                if (booleanExpression == null) {
                    booleanExpression = board.category.eq(category);
                } else {
                    booleanExpression = booleanExpression.or(board.category.eq(category));
                }
            }
        }

        return booleanExpression;
    }

    /**
     * 게시물 작성자 닉네임 EQUALS 검색
     * @param nickname
     * @return
     */
    public static BooleanExpression eqNickname(String nickname){
        return StringUtils.hasText(nickname) ? board.user.nickname.eq(nickname) : null;
    }

    /**
     * VARCHAR 타입의 게시물 조회수 범위검색
     * @param viewFrom
     * @param viewTo
     * @return
     */
    public static BooleanExpression betweenView(String viewFrom, String viewTo){
        if(StringUtils.hasText(viewFrom) && StringUtils.hasText(viewTo)){
            return board.view.castToNum(Long.class)
                    .between(Long.parseLong(viewFrom)
                            , Long.parseLong(viewTo));
        }else if(StringUtils.hasText(viewFrom) && !StringUtils.hasText(viewTo)){
            return board.view.castToNum(Long.class)
                    .gt(Long.parseLong(viewFrom));
        }else if(!StringUtils.hasText(viewFrom) && StringUtils.hasText(viewTo)){
            return board.view.castToNum(Long.class)
                    .lt(Long.parseLong(viewTo));
        }else{
            return null;
        }
    }

    /**
     * 게시물 작성일 범위검색
     * @param registDateTimeFrom
     * @param registDateTimeTo
     * @return
     */
    public static BooleanExpression betweenRegistedDateTime(LocalDateTime registDateTimeFrom, LocalDateTime registDateTimeTo){
        if(registDateTimeFrom != null && registDateTimeTo != null) {
            return board.registedDateTime.between(registDateTimeFrom, registDateTimeTo);
        }else if(registDateTimeFrom != null && registDateTimeTo == null){
            return board.registedDateTime.gt(registDateTimeFrom);
        }else if(registDateTimeFrom == null && registDateTimeTo != null){
            return board.registedDateTime.lt(registDateTimeTo);
        }else{
            return null;
        }
    }
}
