package com.example.demo.src.scrab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrabBanner {
    private String nickname;
    private int scrabTotalCount;
    private int scrabProductCount;
    private int scrabPhotoCount;
}
