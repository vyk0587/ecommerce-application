package com.onejava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.onejava.entity.Product}
 */
@Value
public class ProductDto implements Serializable {
    @NotNull
    @Size(max = 100)
    @NotBlank
    String name;
    @NotBlank
    @Length
    String description;
    BigDecimal price;
}