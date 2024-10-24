package com.hsynayhn.shopServices.Service.CategoryService;

import com.hsynayhn.shopServices.Exception.Category.AlreadyExistsException;
import com.hsynayhn.shopServices.Model.CategoryModel.Category;
import com.hsynayhn.shopServices.Repostory.CategoryRepo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category createCategory(Category newCategory) {

        Optional<Category> categoryByName = categoryRepo.findByCategoryName(newCategory.getCategoryName());
        if (categoryByName.isPresent()) {
            throw new AlreadyExistsException("Il already exists with name: " + newCategory.getCategoryName());
        }

        return categoryRepo.save(newCategory);
    }

}
