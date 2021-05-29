package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.repo;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductInMemoryStorage {

    private final Map<String, Product> PRODUCT_MAP = new LinkedHashMap<>();

    public List<ProductView> getAll() {
        return new ArrayList<>(PRODUCT_MAP.values());
    }

    public Optional<Product> get(String key) {
        return Optional.ofNullable(PRODUCT_MAP.get(key));
    }

    public void persist(String key, Product product) {
        PRODUCT_MAP.put(key, product);
    }

    public void clear() {
        PRODUCT_MAP.clear();
    }
}
