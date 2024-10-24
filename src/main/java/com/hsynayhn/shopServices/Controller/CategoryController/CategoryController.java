package com.hsynayhn.shopServices.Controller.CategoryController;

import com.hsynayhn.shopServices.Model.CategoryModel.Category;
import com.hsynayhn.shopServices.Service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listall")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> getCategoryById(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category), OK);
    }

}
