package com.example.demo.src.category;


import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.model.PostCategoryReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@Controller @RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("")
    public BaseResponse<?> makeNewCategory(PostCategoryReq postCategoryReq){
        categoryService.isCategoryNameExist(postCategoryReq.getCategoryName());
        return new BaseResponse<>(BaseResponseStatus.CATEGORY_EXIST);

    }
    public BaseResponse<?> getAllCategories(){
        return new BaseResponse<>(null);
    }

    public BaseResponse<?> getCategoriesByParentId(){
        return new BaseResponse<>(null);

    }

    public BaseResponse<?> getCategoriesByDepth(){
        return new BaseResponse<>(null);

    }


}
