package be.eekhaut.kristof.aws.shop.warehouse.product.domain;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductCommandService;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.command.AddProductCommand;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = requireNonNull(productRepository);
    }

    @Override
    public String addProduct(AddProductCommand command) {
        ProductId productId = productRepository.generateId();
        Product product = new Product(productId, command.getName(), command.getInitialStock());
        productRepository.add(product);

        return productId.getValue();
    }
}
