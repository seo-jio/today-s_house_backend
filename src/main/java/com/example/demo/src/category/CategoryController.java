package com.example.demo.src.category;


import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.model.Category;
import com.example.demo.src.category.model.PostCategoryReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@Controller @RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("")
    public BaseResponse<?> makeNewCategory(@RequestBody PostCategoryReq postCategoryReq){
        if(categoryService.isCategoryNameExist(postCategoryReq.getCategoryName()))
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_EXIST);
        Long result = categoryService.createNewCategory(postCategoryReq);
        Map<String, String> map = new HashMap<>();
        map.put("url", "/api/categories/"+result.toString());
        return new BaseResponse<>(map);

    }

    @GetMapping("/{categoryId}")
    public BaseResponse<?> getCategoryById(@PathVariable Long categoryId){
        if(!categoryService.isCategoryIdExist(categoryId)){
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }
        return new BaseResponse<>(categoryService.getCategoryById(categoryId));
    }


    @GetMapping("/name/{categoryName}")
    public BaseResponse<?> getCategoryById(@PathVariable String categoryName){
        if(!categoryService.isCategoryNameExist(categoryName)){
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }
        return new BaseResponse<>(categoryService.getCategoryByName(categoryName));
    }

    @PatchMapping("/{categoryId}")
    public BaseResponse<?> updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
        if(!categoryService.isCategoryIdExist(categoryId)){
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }
        if(!categoryService.updateCategory(categoryId, category))
            return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }

    @GetMapping("")
    public BaseResponse<?> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new BaseResponse<>(categories);
    }

    @GetMapping("/{categoryId}/children")
    public BaseResponse<?> getCategoriesByParentId(@PathVariable Long parentId){
        if(!categoryService.isCategoryIdExist(parentId))
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        List<Category> categories = categoryService.getSubCategories(parentId);
        return new BaseResponse<>(categories);
    }

    @GetMapping("/depth/{depth}")
    public BaseResponse<?> getCategoriesByDepth(@PathVariable Integer depth){
        return new BaseResponse<>(categoryService.getCategoryByDepth(depth));
    }


    @DeleteMapping("/{categoryId}")
    public BaseResponse<?> deleteCategoryById(@PathVariable Long categoryId){
        if(!categoryService.isCategoryIdExist(categoryId)){
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }
        try {
            categoryService.deleteById(categoryId);
        }catch (Exception e){
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_CHILDREN_EXIST);
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
