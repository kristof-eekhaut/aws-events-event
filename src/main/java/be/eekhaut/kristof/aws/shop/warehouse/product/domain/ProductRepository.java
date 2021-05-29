package be.eekhaut.kristof.aws.shop.warehouse.product.domain;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findProductById(ProductId productId);
}
