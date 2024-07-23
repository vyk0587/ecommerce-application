package com.onejava.service;

import com.onejava.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductDto> filterProducts(String name, String description, BigDecimal minPrice, BigDecimal maxPrice);

    ProductDto findProductByName(String name);

    List<ProductDto> findProductsByNameContaining(String name);
}
