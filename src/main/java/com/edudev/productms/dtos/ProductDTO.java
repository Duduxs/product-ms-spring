package com.edudev.productms.dtos;

import com.edudev.productms.entities.Product;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;

public final class ProductDTO {

    private final Long id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    public ProductDTO() {
        this.id = 0L;
        this.name = "";
        this.description = "";
        this.price = ONE;
    }

    public ProductDTO(final Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public BigDecimal getPrice() { return price; }

}
