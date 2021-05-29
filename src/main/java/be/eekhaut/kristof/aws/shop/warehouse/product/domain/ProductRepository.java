package be.eekhaut.kristof.aws.shop.warehouse.product.domain;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<ProductView> getAll();

    Optional<Product> findById(ProductId productId);

    ProductId generateId();

    void add(Product product);
}
