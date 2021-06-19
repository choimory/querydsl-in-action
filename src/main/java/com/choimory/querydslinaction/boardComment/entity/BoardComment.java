package com.choimory.querydslinaction.boardComment.entity;

import com.choimory.querydslinaction.board.entity.Board;
import com.choimory.querydslinaction.common.entity.CommonEntity;
import com.choimory.querydslinaction.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardComment extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @ManyToOne
    @JoinColumn(name = "BOARD_IDX")
    private Board board;
    @OneToOne
    @JoinColumn(name = "USER_IDX")
    private User user;
    @Column(nullable = false)
    private String comment;
}
