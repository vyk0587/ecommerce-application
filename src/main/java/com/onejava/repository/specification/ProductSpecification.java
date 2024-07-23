package com.onejava.repository.specification;

import com.onejava.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    /*
     * This method is used to create a Specification object that can be used to filter products based on the given name.
     */
    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    /*
     * This method is used to create a Specification object that can be used to filter products based on the given description.
     */
    public static Specification<Product> descriptionContains(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    /*
     * This method is used to create a Specification object that can be used to filter products based on the given price.
     */
    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    /*
    We can use either the above approach or the below approach to create a Specification object that can be used to filter products based on the given attributes.

    * This method is used to create a Specification object that can be used to filter products based on the given attributes.
     */
    public static Specification<Product> hasAttributes(String name, String description, BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (description != null) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }
            if (minPrice != null && maxPrice != null) {
                predicates.add(criteriaBuilder.between(root.get("price"), minPrice, maxPrice));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}