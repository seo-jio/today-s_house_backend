package com.example.demo.src.photo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPhotoReq {
    private Long userIdx;
    private String type;
    private String photoUrl;
    @Nullable
    private String text;
}
