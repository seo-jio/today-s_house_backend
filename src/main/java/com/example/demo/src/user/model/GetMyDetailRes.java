package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMyDetailRes {
    private String profileImageUrl;
    private String nickname;
    private int followerCount;
    private int followingCount;
    private int photoCount;
    private String LPhotoUrl;
    private String BPhotoUrl;
    private String KPhotoUrl;
    private String LIPhotoUrl;
    private String VPhotoUrl;
    private String BAPhotoUrl;
    private String DPhotoUrl;
    private String FPhotoUrl;
    private int scrabItemCount;
    private List<String> scrabItemImageUrls;
}
