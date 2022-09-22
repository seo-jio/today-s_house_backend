package com.example.demo.src.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchPhotoTextReq {
    private Long userIdx;
    private Long photoId;
    private String text;
}
