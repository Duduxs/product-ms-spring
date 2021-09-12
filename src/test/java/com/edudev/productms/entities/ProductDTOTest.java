package com.edudev.productms.entities;

import com.edudev.productms.dtos.ProductDTO;
import com.edudev.productms.exceptions.BadRequestHttpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.edudev.productms.exceptions.handler.ConstraintViolationHandler.validate;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductDTOTest {

    private final Product product = new Product(1L, "Test product", "Test Description", BigDecimal.valueOf(24.56));

    private final ProductHelper helper = new ProductHelper();

    @Test
    @DisplayName("ProductDTO must be instantiable")
    public void createProductDTO() {
        var dto = new ProductDTO(product);
        helper.assertProductEquals(product, dto);
    }

    @Test
    @DisplayName("ProductDTO must update a product instance")
    public void updateProduct() {
        var dto = new ProductDTO(product);
        var newProduct = new Product(dto.getId());

        dtoToProduct(dto, newProduct);
        helper.assertProductEquals(newProduct, dto);
    }

    @Test
    @DisplayName("Must not update for passing a negative product price")
    public void mustThrowErrorByNotPositivePrice() {
        var product = new Product(1L, "Product name", "Product description", ZERO);
        var dto = new ProductDTO(product);

        assertThrows(
                BadRequestHttpException.class,
                () -> dtoToProduct(dto, product)
        );
    }

    @Test
    @DisplayName("Must not update for passing blank to properties not blank ")
    public void mustThrowErrorByPassBlank() {
        var product = new Product(1L, "", "", ONE);
        var dto = new ProductDTO(product);

        assertThrows(
                BadRequestHttpException.class,
                () -> dtoToProduct(dto, product)
        );
    }

    @Test
    @DisplayName("Must not update for passing null to properties not null ")
    public void mustThrowErrorByPassNull() {
        var product = new Product(null, null, null, ONE);
        var dto = new ProductDTO(product);

        assertThrows(
                BadRequestHttpException.class,
                () -> dtoToProduct(dto, product)
        );
    }

    private void dtoToProduct(final ProductDTO dto, final Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        validate(product);
    }
}
