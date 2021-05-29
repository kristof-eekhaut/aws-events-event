package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller;

import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductQueryService;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.ProductView;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping(value = "/products", produces = { MediaTypes.HAL_JSON_VALUE })
public class ProductController {

    private final ProductQueryService productQueryService;
    private final ProductRepresentationAssembler productRepresentationAssembler;

    public ProductController(ProductQueryService productQueryService,
                             ProductRepresentationAssembler productRepresentationAssembler) {
        this.productQueryService = requireNonNull(productQueryService);
        this.productRepresentationAssembler = requireNonNull(productRepresentationAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRepresentation> getProductById(@PathVariable("id") String id) {
        Optional<ProductView> product = productQueryService.findProductById(id);

        if (product.isEmpty())
            return ResponseEntity.notFound().build();

        ProductRepresentation productRepresentation = productRepresentationAssembler.toModel(product.get());
        return ResponseEntity.ok(productRepresentation);
    }
}
