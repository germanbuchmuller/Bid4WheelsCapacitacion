package com.example.demo.service.impl;

import com.example.demo.dto.CreateProductDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductDTO createProduct(CreateProductDTO createProductDTO) {
        Product product = Product.builder()
                .name(createProductDTO.getName())
                .price(createProductDTO.getPrice())
                .build();
        return productRepository.save(product).toDTO();
    }

    @Override
    public Optional<Product> updateProduct(CreateProductDTO createProductDTO, UUID uuid) {
        Optional<Product> productToModify = productRepository.findById(uuid);
        if(productToModify.isPresent()){
            Product product = productToModify.get();
            product.setName(createProductDTO.getName());
            product.setPrice(createProductDTO.getPrice());
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    @Override
    public void deleteProduct(UUID uuid) {
        if (productRepository.existsById(uuid)) {
            productRepository.deleteById(uuid);
        }
    }

    @Override
    public Optional<Product> getProductByID(UUID uuid) {
        Optional<Product> product = productRepository.findById(uuid);
        if(product.isPresent()){
            return product;
        }else {
            throw new EntityNotFoundException("Product with id: " + uuid + " not found");
        }
    }
}
