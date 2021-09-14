package io.github.emvnuel.statefulapp.product;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageBase64;
}
