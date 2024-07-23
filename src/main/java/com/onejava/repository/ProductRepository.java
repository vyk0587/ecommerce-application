package com.onejava.repository;

import com.onejava.dto.ProductDto;
import com.onejava.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Optional<ProductDto> findByName(String name);

    List<ProductDto> findByNameContaining(String name);

}