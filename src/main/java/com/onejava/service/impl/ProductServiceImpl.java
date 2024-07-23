package com.onejava.service.impl;

import com.onejava.constant.TypeReferenceConstant;
import com.onejava.dto.ProductDto;
import com.onejava.entity.Product;
import com.onejava.repository.ProductRepository;
import com.onejava.repository.specification.ProductSpecification;
import com.onejava.service.ProductService;
import com.onejava.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
     * This method is used to filter products based on the given attributes.
     */
    public List<ProductDto> filterProducts(String name, String description, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(ProductSpecification.hasName(name));
        }
        if (description != null && !description.isEmpty()) {
            spec = spec.and(ProductSpecification.descriptionContains(description));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }

        // You can use either of the below approaches to filter products based on the given attributes.
        List<Product> products = productRepository.findAll(spec);
        // List<Product> products = productRepository.findAll(ProductSpecification.hasAttributes(name, description, minPrice, maxPrice));

        return ModelMapper
                .convert(products, TypeReferenceConstant.LIST_OF_PRODUCT_DTO_TYPE_REFERENCE);
    }

    /*
     * This method is used to find a product by its name.
     */
    @Override
    public ProductDto findProductByName(String name) {
        return productRepository.findByName(name).orElse(null);

    }

    /*
     * This method is used to find products by name containing.
     */
    @Override
    public List<ProductDto> findProductsByNameContaining(String description) {
        return productRepository.findByNameContaining(description);
    }
}