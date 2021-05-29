package be.eekhaut.kristof.aws.shop.warehouse.product.application.api.command;

public class AddProductCommand {

    private final String name;
    private final int initialStock;

    public AddProductCommand(String name,
                              int initialStock) {
        this.name = name;
        this.initialStock = initialStock;
    }

    public String getName() {
        return name;
    }

    public int getInitialStock() {
        return initialStock;
    }
}
