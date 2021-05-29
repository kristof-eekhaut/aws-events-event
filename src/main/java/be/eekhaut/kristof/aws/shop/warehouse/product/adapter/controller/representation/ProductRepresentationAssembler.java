package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller.representation;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductRepresentationAssembler implements RepresentationModelAssembler<ProductView, ProductRepresentation> {

    @Override
    public ProductRepresentation toModel(ProductView entity) {
        return ProductRepresentation.builder()
                .id(entity.getId().getValue())
                .name(entity.getName())
                .itemsInStock(entity.getItemsInStock())
                .build();
    }
}
