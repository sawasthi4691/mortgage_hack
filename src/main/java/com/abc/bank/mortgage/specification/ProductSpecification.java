package com.abc.bank.mortgage.specification;

import com.abc.bank.mortgage.models.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null || category.isEmpty()) {
                return  null; //Ignore this filter if no value is provided
            }
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return  null; //Ignore this filter if no value is provided
            }
            if(minPrice != null && maxPrice !=null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            } else if(minPrice != null) {
                return  criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
        };
    }

    public static Specification<Product> isAvailbale(Boolean available) {
        return (root, query, criteriaBuilder) -> {
            if (available == null) {
                return  null; //Ignore this filter if no value is provided
            }
            return criteriaBuilder.equal(root.get("available"), available);
        };
    }
}
