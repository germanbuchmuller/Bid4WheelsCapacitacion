package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        val products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping
    public ResponseEntity<Product> postNewProduct(@RequestBody ProductDTO productDTO){
        val product = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByID(@PathVariable UUID id){
        val product = productService.getProductByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO,@PathVariable UUID id){
        val product = productService.updateProduct(productDTO,id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByID(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
