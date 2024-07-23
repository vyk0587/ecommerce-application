package com.onejava.constant;

import com.fasterxml.jackson.core.type.TypeReference;
import com.onejava.dto.ProductDto;

import java.util.List;

public class TypeReferenceConstant {
    public static final TypeReference<List<ProductDto>> LIST_OF_PRODUCT_DTO_TYPE_REFERENCE = new TypeReference<>() {
    };
}
