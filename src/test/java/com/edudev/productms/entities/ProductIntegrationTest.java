package com.edudev.productms.entities;

import com.edudev.productms.dtos.ProductDTO;
import com.edudev.productms.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.math.BigDecimal.valueOf;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @Autowired
    private ProductRepository repository;


    @Test
    @DisplayName("Must be able to insert product")
    public void mustCreateProduct() {
        final Product product = new Product(0L, "Product Insert", "Product Description", valueOf(59L));

        try {
            final ProductDTO insertedProduct = given()
                    .contentType(JSON)
                    .body(new ProductDTO(product))
                    .when().post()
                    .then().statusCode(201)
                    .extract().as(ProductDTO.class);

//            helper.assertProductEquals(product, insertedProduct);
        } finally {
            Assertions.assertThat(repository.count()).isEqualTo(0L);
        }
    }


//    @Test
//    @DisplayName("Must be able to insert product")
//    public void mustCreateProduct() {
//        final Product product = new Product(1L, "Product Insert", "Product Description", valueOf(59L));
//
//        try {
//            final ProductDTO insertedProduct = given()
//                    .contentType(JSON)
//                    .body(new ProductDTO(product))
//                    .when().post()
//                    .then().statusCode(201)
//                    .extract().as(ProductDTO.class);
//
//            helper.assertProductEquals(product, insertedProduct);
//        } finally {
//            Assertions.assertThat(repository.count()).isEqualTo(0L);
//        }
//    }

}
