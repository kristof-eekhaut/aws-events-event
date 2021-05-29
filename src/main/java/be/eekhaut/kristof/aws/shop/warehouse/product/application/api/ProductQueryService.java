package be.eekhaut.kristof.aws.shop.warehouse.product.application.api;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {

    List<ProductView> getProducts();

    Optional<ProductView> findProductById(String id);
}
