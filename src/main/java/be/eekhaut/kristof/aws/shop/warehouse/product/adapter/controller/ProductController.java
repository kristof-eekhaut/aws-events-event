package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller;

import be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller.representation.ProductRepresentation;
import be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller.representation.ProductRepresentationAssembler;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductCommandService;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductQueryService;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.command.AddProductCommand;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.view.ProductView;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/products", produces = { MediaTypes.HAL_JSON_VALUE })
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;
    private final ProductRepresentationAssembler productRepresentationAssembler;

    public ProductController(ProductQueryService productQueryService,
                             ProductCommandService productCommandService,
                             ProductRepresentationAssembler productRepresentationAssembler) {
        this.productQueryService = requireNonNull(productQueryService);
        this.productCommandService = requireNonNull(productCommandService);
        this.productRepresentationAssembler = requireNonNull(productRepresentationAssembler);
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<ProductRepresentation>> getAllProducts() {
        List<ProductView> products = productQueryService.getProducts();

        CollectionModel<ProductRepresentation> representation = productRepresentationAssembler.toCollectionModel(products);
        return ResponseEntity.ok(representation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRepresentation> getProductById(@PathVariable("id") String id) {
        Optional<ProductView> product = productQueryService.findProductById(id);

        if (product.isEmpty())
            return ResponseEntity.notFound().build();

        ProductRepresentation representation = productRepresentationAssembler.toModel(product.get());
        return ResponseEntity.ok(representation);
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody AddProductCommand command) {
        String id = productCommandService.addProduct(command);

        return ResponseEntity.accepted()
                .location(linkTo(methodOn(ProductController.class).getProductById(id)).toUri())
                .build();
    }
}
