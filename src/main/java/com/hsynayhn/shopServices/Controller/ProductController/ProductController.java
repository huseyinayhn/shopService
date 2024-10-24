package com.hsynayhn.shopServices.Controller.ProductController;


import com.hsynayhn.shopServices.Model.ProductModel.Product;
import com.hsynayhn.shopServices.Service.ProductService.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
    @RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>>listProdcuts(){
        return new ResponseEntity<>(productService.getAllProducts(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>>getProductById(@PathVariable String id){
        return new ResponseEntity<>(productService.getProductById(id), OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<Product>>listProdcutByCategoryId(@PathVariable String id) {
        return new ResponseEntity<>(productService.getAllProductsByCategoryId(id), OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product>createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), OK);
    }

    @DeleteMapping("/delete/{id}")
    public Optional<Product>deleteProduct(@PathVariable String id) {
        return productService.deleteProductById(id);
    }

    @PutMapping("/update/{id}")
    public Optional<Product>updateProduct(@RequestBody Product product, @PathVariable String id) {
        return productService.updateProduct(id, product);
    }

}
