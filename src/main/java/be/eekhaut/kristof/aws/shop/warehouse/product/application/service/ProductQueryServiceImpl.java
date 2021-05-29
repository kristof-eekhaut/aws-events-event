package be.eekhaut.kristof.aws.shop.warehouse.product.application.service;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductQueryService;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId.productId;
import static java.util.Objects.requireNonNull;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = requireNonNull(productRepository);
    }

    @Override
    public List<ProductView> getProducts() {
        return productRepository.getAll();
    }

    @Override
    public Optional<ProductView> findProductById(String id) {
        return productRepository.findById(productId(id))
                .map(ProductView.class::cast);
    }
}
