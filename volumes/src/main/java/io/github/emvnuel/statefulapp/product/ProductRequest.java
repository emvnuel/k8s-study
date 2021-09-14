package io.github.emvnuel.statefulapp.product;

import io.github.emvnuel.statefulapp.product.Product;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Value
public class ProductRequest {

    @NotEmpty
    String name;

    @NotEmpty
    String description;

    @Positive
    BigDecimal price;


    public Product toModel() {
        return new Product(name, description, price);
    }
}
