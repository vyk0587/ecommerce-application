package com.onejava.controller;

import com.onejava.dto.ProductDto;
import com.onejava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
     * This method is used to filter products based on the given attributes.
     */
    @GetMapping("/filter")
    public List<ProductDto> filterProducts(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String description,
                                           @RequestParam(required = false) BigDecimal minPrice,
                                           @RequestParam(required = false) BigDecimal maxPrice) {
        return productService.filterProducts(name, description, minPrice, maxPrice);
    }

    /*
     * This method is used to find a product by its name.
     */
    @GetMapping("/name")
    public ProductDto findProductByName(@RequestParam String name) {
        return productService.findProductByName(name);
    }


    /*
     * This method is used to find products by name containing.
     */
    @GetMapping("/nameContaining")
    public List<ProductDto> findProductsByNameContaining(@RequestParam String name) {
        return productService.findProductsByNameContaining(name);
    }

}