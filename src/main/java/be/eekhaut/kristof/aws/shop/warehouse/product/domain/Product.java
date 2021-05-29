package be.eekhaut.kristof.aws.shop.warehouse.product.domain;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductView;

import static java.util.Objects.requireNonNull;

public class Product implements ProductView {

    private final ProductId id;
    private String name;
    private int itemsInStock;

    public Product(ProductId id, String name, int initialStock) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.itemsInStock = initialStock;
    }

    @Override
    public ProductId getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getItemsInStock() {
        return itemsInStock;
    }
}
