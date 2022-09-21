package com.example.demo.src.product;

import com.example.demo.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductProvider productProvider;
    private final ProductService productService;

    @GetMapping("")
    BaseResponse<?> getProductMain(){
        return new BaseResponse<>(productProvider.getMainProducts());
    }



}
