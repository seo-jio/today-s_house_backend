package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFollowRes {
    private Long userIdx;
    private String profileImageUrl;
    private String nickname;
}
