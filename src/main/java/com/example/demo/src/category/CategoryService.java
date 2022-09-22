package com.example.demo.src.category;

import com.example.demo.src.category.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao;

    public List<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    public List<Category> getSubCategories(Long parentCategoryId) {
        return categoryDao.findByParentCategoryId(parentCategoryId);
    }

    public Boolean isCategoryNameExist(String categoryName){
        return categoryDao.isCategoryNameExist(categoryName);
    }
}
