package com.hsynayhn.shopServices.Repostory.ProductRepo;

import com.hsynayhn.shopServices.Model.CategoryModel.Category;
import com.hsynayhn.shopServices.Model.ProductModel.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findAllByName(String Name);
    Optional<Product> findByName(String Name);
}
