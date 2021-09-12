package com.edudev.productms.services;

import com.edudev.productms.dtos.ProductDTO;
import com.edudev.productms.entities.Product;
import com.edudev.productms.exceptions.ConflictHttpException;
import com.edudev.productms.exceptions.NotFoundHttpException;
import com.edudev.productms.repositories.ProductRepository;
import com.edudev.productms.utils.FilterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Map;

import static com.edudev.productms.exceptions.handler.ConstraintViolationHandler.validate;
import static java.util.stream.Collectors.toList;

@Service
public final class ProductService {

    @Autowired
    private ProductRepository repository;

    @PersistenceContext
    private EntityManager em;

    public ProductDTO findById(final Long id) {
        return repository.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(NotFoundHttpException::new);
    }

    public Page<ProductDTO> findAllPaged(final PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(ProductDTO::new);
    }

    public Collection<ProductDTO> findAllSearched(final FilterParam filterParam) {
        return createQuery(filterParam);
    }

    public ProductDTO insert(final ProductDTO dto) {
        if (repository.existsById(dto.getId()))
            throw new ConflictHttpException("Entidade com id já cadastrados no banco");

        Product product = new Product();
        dtoToProduct(dto, product);

        product = repository.save(product);
        return new ProductDTO(product);
    }

    public ProductDTO update(final Long id, final ProductDTO dto) {
        final Product product = repository.findById(id).orElseThrow(NotFoundHttpException::new);
        dtoToProduct(dto, product);

        repository.save(product);
        return new ProductDTO(product);
    }


    public void delete(final Long id) {
        try {
            repository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundHttpException();
        }
    }

    private void dtoToProduct(final ProductDTO dto, final Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        validate(product);
    }

    public Collection<ProductDTO> createQuery(final FilterParam filter) {

        final var builder = em.getCriteriaBuilder();

        final CriteriaQuery<Product> query = builder.createQuery(Product.class);
        final Root<Product> p = query.from(Product.class);

        final Map<Predicate, Boolean> filters = Map.of(
                builder.or(
                        builder.like(p.get("name"), "%" + filter.getQ() + "%"),
                        builder.like(p.get("description"), "%" + filter.getQ() + "%")
                ), !filter.getQ().isEmpty(),
                builder.greaterThanOrEqualTo(p.get("price"), filter.getMinPrice()), filter.getMinPrice() != 0,
                builder.lessThanOrEqualTo(p.get("price"), filter.getMaxPrice()), filter.getMaxPrice() != 0
        );

        final var valids = filters.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).toArray(Predicate[]::new);

        return em
                .createQuery(query.where(valids))
                .getResultStream()
                .map(ProductDTO::new)
                .collect(toList());
    }


}
