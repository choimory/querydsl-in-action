package com.choimory.querydslinaction.user.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long idx;
    private String id;
    private String nickname;
    private String password;
    private String email;
    private String address;
    private String introduce;
    private Long point;
}
