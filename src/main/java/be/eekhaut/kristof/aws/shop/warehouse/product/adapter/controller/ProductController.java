package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products", produces = { MediaTypes.HAL_JSON_VALUE })
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable("id") String id) {
        ProductResource productResource = ProductResource.builder()
                .id("3")
                .name("Leffe")
                .itemsInStock(10)
                .build();
        return ResponseEntity.ok(productResource);
    }
}
