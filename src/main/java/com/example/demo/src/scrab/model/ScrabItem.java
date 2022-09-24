package com.example.demo.src.scrab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrabItem implements Comparable<ScrabItem>{
    private String type; //Product, Photo
    private Long itemId;
    private String imageUrl; //이미지 url
    private LocalDateTime createdAt; // 정렬를 위해 필요

    @Override
    public int compareTo(ScrabItem o1) {
        return this.createdAt.compareTo(o1.getCreatedAt());
    }
}
