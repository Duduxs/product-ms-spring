package com.edudev.productms.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;

@Entity
@Table(name = "tb_product")
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @NotBlank(message = "Name é obrigatório")
    private String name;

    @NotBlank(message = "Description é obrigatório")
    private String description;

    @NotNull
    @Min(value = 1, message = "Preço deve ser positivo")
    private BigDecimal price;

    public Product() {
        this(0L, "", "", ONE);
    }

    public Product(final Long id, final String name, final String description, final BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() { return this.id; }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(final String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(final BigDecimal price) { this.price = price; }
}