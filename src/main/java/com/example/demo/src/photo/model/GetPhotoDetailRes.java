package com.example.demo.src.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoDetailRes {
    private Long photoId;
    private Long userIdx;
    private String type;
    private String photoUrl;
    private String text;
}
