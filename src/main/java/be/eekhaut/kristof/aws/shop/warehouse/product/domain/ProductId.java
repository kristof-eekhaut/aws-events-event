package be.eekhaut.kristof.aws.shop.warehouse.product.domain;

import static java.util.Objects.requireNonNull;

public class ProductId {

    private final String value;

    public static ProductId productId(String value) {
        return new ProductId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductId productId = (ProductId) o;

        return value != null ? value.equals(productId.value) : productId.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    private ProductId(String value) {
        this.value = requireNonNull(value);
    }
}
