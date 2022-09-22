package com.example.demo.src.product;

import com.example.demo.src.product.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
}
