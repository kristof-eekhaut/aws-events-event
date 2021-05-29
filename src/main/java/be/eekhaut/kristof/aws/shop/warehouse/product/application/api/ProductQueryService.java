package be.eekhaut.kristof.aws.shop.warehouse.product.application.api;

import java.util.Optional;

public interface ProductQueryService {

    Optional<ProductView> findProductById(String id);
}
