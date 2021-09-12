package com.edudev.productms.resources;

import com.edudev.productms.dtos.ProductDTO;
import com.edudev.productms.services.ProductService;
import com.edudev.productms.utils.FilterParam;
import com.edudev.productms.utils.PageParam;
import com.edudev.productms.utils.SortParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/products")
@Api("Product MS")
@CrossOrigin(origins = "*")
public final class ProductResource {

    @Autowired
    ProductService service;

    @GetMapping("/{id}")
    @ApiOperation("Find a product searched by its id ")
    public ResponseEntity<ProductDTO> findById(final @PathVariable Long id) {
        final ProductDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @ApiOperation("Return a paged product list")
    public ResponseEntity<Page<ProductDTO>> findAll(final PageParam pageParam, final SortParam sortParam) {
        final PageRequest pr = PageRequest.of(
                pageParam.getPage(),
                pageParam.getSize(),
                Direction.valueOf(sortParam.getDirection()),
                sortParam.getOrderBy()
        );

        final Page<ProductDTO> product = service.findAllPaged(pr);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    @ApiOperation("Search product list based in query params passed in URI (q, min_price, max_price)")
    public ResponseEntity<Collection<ProductDTO>> findAllSearched(
            final @RequestParam(value = "q", defaultValue = "") String query,
            final @RequestParam(value = "min_price", defaultValue = "0.0") Double minPrice,
            final @RequestParam(value = "max_price", defaultValue = "0.0") Double maxPrice
    ) {
        final Collection<ProductDTO> dtos = service.findAllSearched(new FilterParam(query, minPrice, maxPrice));
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    @ApiOperation("Insert a product")
    public ResponseEntity<ProductDTO> insert(final @Valid @RequestBody ProductDTO dto) {
        final ProductDTO insertedDTO = service.insert(dto);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(insertedDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(insertedDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a existing product by passing its id and a new product dto")
    public ResponseEntity<ProductDTO> update(final @PathVariable Long id, final @Valid @RequestBody ProductDTO dto) {
        var dtoUpdated = service.update(id, dto);
        return ResponseEntity.ok().body(dtoUpdated);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a product by id")
    public ResponseEntity<Void> delete(final @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
