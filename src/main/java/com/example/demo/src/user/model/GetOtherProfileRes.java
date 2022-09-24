package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOtherProfileRes {
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

    public GetOtherProfileRes(String profileImageUrl, String nickname, int followerCount, int followingCount, int photoCount, String LPhotoUrl, String BPhotoUrl, String KPhotoUrl, String LIPhotoUrl, String VPhotoUrl, String BAPhotoUrl, String DPhotoUrl, String FPhotoUrl) {
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.photoCount = photoCount;
        this.LPhotoUrl = LPhotoUrl;
        this.BPhotoUrl = BPhotoUrl;
        this.KPhotoUrl = KPhotoUrl;
        this.LIPhotoUrl = LIPhotoUrl;
        this.VPhotoUrl = VPhotoUrl;
        this.BAPhotoUrl = BAPhotoUrl;
        this.DPhotoUrl = DPhotoUrl;
        this.FPhotoUrl = FPhotoUrl;
    }
}
