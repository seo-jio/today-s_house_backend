package com.example.demo.src.category;

import com.example.demo.src.category.model.Category;
import com.example.demo.src.category.model.PostCategoryReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
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

    public Boolean isCategoryIdExist(Long categoryId){
        return categoryDao.isCategoryIdExist(categoryId);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryDao.findByCategoryId(categoryId);
    }

    public Category getCategoryByName(String categoryName) {
        return categoryDao.findByCategoryName(categoryName);
    }

    public Boolean updateCategory(Long categoryId, Category category) {
        int new_depth = getCategoryById(category.getParentCategoryId()).getDepth() + 1;
        return categoryDao.updateCategory(categoryId, category.getCategoryName(), category.getParentCategoryId(), new_depth);
    }

    public List<Category> getCategoryByDepth(Integer depth) {
        return categoryDao.findByDepth(depth);
    }

    public Long createNewCategory(PostCategoryReq postCategoryReq) {
        int new_depth = getCategoryById(postCategoryReq.getParentCategoryId()).getDepth() + 1;
        return categoryDao.createCategory(postCategoryReq, new_depth);
    }

    public void deleteById(Long categoryId) {
        categoryDao.deleteByCategoryId(categoryId);
    }

    public List<Category> getCategoryTree(Long category2) {
        List<Category> result = new ArrayList<>();
        while(category2 != 0) {
            Category c = getCategoryById(category2);
            result.add(c);
            category2 = c.getParentCategoryId();
        }
        Collections.reverse(result);
        return result;
    }
}
