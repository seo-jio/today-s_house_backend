package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchEditProfileReq {
    private String profileImageUrl;
    private String nickname;
    private String profileText;
    private String snsUrl;
}
