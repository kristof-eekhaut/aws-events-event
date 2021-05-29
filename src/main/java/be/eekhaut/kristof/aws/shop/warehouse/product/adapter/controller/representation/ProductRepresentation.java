package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller.representation;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(value = "product", collectionRelation = "products")
public class ProductRepresentation extends RepresentationModel<ProductRepresentation> {

    private final String id;
    private final String name;
    private final int itemsInStock;

    private ProductRepresentation(Builder builder) {
        id = builder.id;
        name = builder.name;
        itemsInStock = builder.itemsInStock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getItemsInStock() {
        return itemsInStock;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String name;
        private int itemsInStock;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder itemsInStock(int itemsInStock) {
            this.itemsInStock = itemsInStock;
            return this;
        }

        public ProductRepresentation build() {
            return new ProductRepresentation(this);
        }
    }
}
