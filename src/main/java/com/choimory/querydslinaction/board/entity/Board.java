package com.choimory.querydslinaction.board.entity;


import com.choimory.querydslinaction.boardComment.entity.BoardComment;
import com.choimory.querydslinaction.common.entity.CommonEntity;
import com.choimory.querydslinaction.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    @Lob
    private String content;
    @OneToOne
    @JoinColumn(name = "USER_IDX")
    private User user;
    @Column(nullable = false, columnDefinition = "BIGINT default ('0')")
    private String view; // Varchar 타입의 숫자 범위검색 방법을 학습하기 위해 일부러 String으로 정의
    @Column(nullable = false, columnDefinition = "BIGINT default (0)")
    private Long upVote;
    @Column(nullable = false, columnDefinition = "BIGINT default (0)")
    private Long downVote;
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardComment> boardComments;
}
