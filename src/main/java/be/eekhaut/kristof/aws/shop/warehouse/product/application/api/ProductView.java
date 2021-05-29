package be.eekhaut.kristof.aws.shop.warehouse.product.application.api;

import be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId;

public interface ProductView {

    ProductId getId();
    String getName();
    int getItemsInStock();
}
