package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.repo;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.Product;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId.productId;
import static java.util.Objects.requireNonNull;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductInMemoryStorage productInMemoryStorage;

    public ProductRepositoryImpl(ProductInMemoryStorage productInMemoryStorage) {
        this.productInMemoryStorage = requireNonNull(productInMemoryStorage);
    }

    @Override
    public List<ProductView> getAll() {
        return productInMemoryStorage.getAll();
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productInMemoryStorage.get(productId.getValue());
    }

    @Override
    public ProductId generateId() {
        return productId(UUID.randomUUID().toString());
    }

    @Override
    public void add(Product product) {
        productInMemoryStorage.persist(product.getId().getValue(), product);
    }
}
