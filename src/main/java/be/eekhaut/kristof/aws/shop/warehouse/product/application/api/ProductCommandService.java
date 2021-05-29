package be.eekhaut.kristof.aws.shop.warehouse.product.application.api;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.command.AddProductCommand;

public interface ProductCommandService {

    String addProduct(AddProductCommand command);
}
