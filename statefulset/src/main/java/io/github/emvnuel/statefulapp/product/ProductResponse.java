package io.github.emvnuel.statefulapp.product;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductResponse {

    Long id;

    String name;

    String description;

    BigDecimal price;

    String imageBase64;
}
