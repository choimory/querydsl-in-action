package com.choimory.querydslinaction.user.entity;

import com.choimory.querydslinaction.common.entity.CommonEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(unique = true, nullable = false, updatable = false)
    private String id;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;
    @Column
    private String email;
    @Column
    private String address;
    @Column
    private String introduce;
    @Column
    private Long point;
}
