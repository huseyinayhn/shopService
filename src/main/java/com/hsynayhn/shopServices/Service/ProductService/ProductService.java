package com.hsynayhn.shopServices.Service.ProductService;

import com.hsynayhn.shopServices.Exception.Category.AlreadyExistsException;
import com.hsynayhn.shopServices.Model.CategoryModel.Category;
import com.hsynayhn.shopServices.Model.ProductModel.Product;
import com.hsynayhn.shopServices.Repostory.CategoryRepo.CategoryRepo;
import com.hsynayhn.shopServices.Repostory.ProductRepo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepo.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(String categoryId) {
        List<Product> products = productRepo.findAll();

        return products.stream()
                .filter(product -> product.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public Product createProduct(Product newProduct) {

        newProduct.setId((UUID.randomUUID().toString()));

        Optional<Product> productByName = productRepo.findByName(newProduct.getName());
        if (productByName.isPresent()) {
            throw new AlreadyExistsException("Il already exists with name: " + newProduct.getName());
        }

        List<Category> categorys = categoryRepo.findAll();

        boolean categoryExists = categorys.stream()
                .anyMatch(category -> category.getId().equals(newProduct.getCategoryId()));

        if (!categoryExists) {
            throw new AlreadyExistsException("Category does not exist with id: " + newProduct.getCategoryId());
        }

        return productRepo.save(newProduct);
    }

    public Optional<Product>deleteProductById(String id) {
        Optional<Product> productById = productRepo.findById(id);
        productRepo.deleteById(id);
        return productById;
    }

    public Optional<Product>updateProduct(String id, Product newProduct) {
        Optional<Product> productById = productRepo.findById(id);
        if (productById.isPresent()) {
            Optional.ofNullable(newProduct.getName())
                    .ifPresent(productById.get()::setName);

            Optional.ofNullable(newProduct.getDescription())
                    .ifPresent(productById.get()::setDescription);

            Optional.ofNullable(newProduct.getCategoryId())
                    .ifPresent(productById.get()::setCategoryId);
            if (newProduct.getPrice() > 0.0) {
                productById.get().setPrice(newProduct.getPrice());
            }
            if (newProduct.getQuantity() > 0) {
                productById.get().setQuantity(newProduct.getQuantity());
            }
            productRepo.save(productById.get());
        }
       return productById;
    }

}
