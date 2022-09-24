package com.example.demo.src.scrab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrabProductWrapper {  //모두, 사진 탭에서 사용
    private ScrabBanner scrabBanner;
    private List<ScrabProduct> scrabItems;
}
