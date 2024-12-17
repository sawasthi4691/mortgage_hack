package com.abc.bank.mortgage.service;


import com.abc.bank.mortgage.models.Product;
import com.abc.bank.mortgage.repository.ProductRepository;
import com.abc.bank.mortgage.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> search(String category, Double minPrice, Double maxPrice, Boolean available) {
        Specification<Product> specification = Specification
                .where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.hasPriceBetween(minPrice, maxPrice))
                .and(ProductSpecification.isAvailbale(available));
        return productRepository.findAll(specification);
    }
}
