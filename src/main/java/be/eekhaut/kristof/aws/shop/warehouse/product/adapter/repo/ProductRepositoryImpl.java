package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.repo;

import be.eekhaut.kristof.aws.shop.warehouse.product.domain.Product;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId.productId;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final Map<ProductId, Product> PRODUCT_MAP = new HashMap<>();

    static {
        persistProduct(new Product(productId("3"), "Leffe", 10));
    }

    @Override
    public Optional<Product> findProductById(ProductId productId) {
        return Optional.ofNullable(PRODUCT_MAP.get(productId));
    }

    private static void persistProduct(Product product) {
        PRODUCT_MAP.put(product.getId(), product);
    }
}
