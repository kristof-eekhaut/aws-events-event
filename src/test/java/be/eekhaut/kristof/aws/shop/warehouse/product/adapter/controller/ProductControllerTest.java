package be.eekhaut.kristof.aws.shop.warehouse.product.adapter.controller;

import be.eekhaut.kristof.aws.shop.warehouse.product.adapter.repo.ProductInMemoryStorage;
import be.eekhaut.kristof.aws.shop.warehouse.product.application.api.command.AddProductCommand;
import be.eekhaut.kristof.aws.shop.warehouse.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static be.eekhaut.kristof.aws.shop.warehouse.product.domain.ProductId.productId;
import static be.eekhaut.kristof.aws.shop.warehouse.product.test.TestConstants.BASE_URL;
import static be.eekhaut.kristof.aws.shop.warehouse.product.test.TestConstants.UUID_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductInMemoryStorage productInMemoryStorage;

    @BeforeEach
    void setUp() {
        productInMemoryStorage.clear();

        productInMemoryStorage.persist("3", new Product(productId("3"), "Leffe", 10));
        productInMemoryStorage.persist("4", new Product(productId("4"), "Gouden Carolus Tripel", 30));
        productInMemoryStorage.persist("5", new Product(productId("5"), "Duvel", 18));
    }

    @Test
    void shouldReturnAllProducts() throws Exception {

        mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("_embedded.products", hasSize(3)))

                .andExpect(jsonPath("_embedded.products[0].id").value(3))
                .andExpect(jsonPath("_embedded.products[0].name").value("Leffe"))
                .andExpect(jsonPath("_embedded.products[0].itemsInStock").value(10))

                .andExpect(jsonPath("_embedded.products[1].id").value(4))
                .andExpect(jsonPath("_embedded.products[1].name").value("Gouden Carolus Tripel"))
                .andExpect(jsonPath("_embedded.products[1].itemsInStock").value(30))

                .andExpect(jsonPath("_embedded.products[2].id").value(5))
                .andExpect(jsonPath("_embedded.products[2].name").value("Duvel"))
                .andExpect(jsonPath("_embedded.products[2].itemsInStock").value(18));
    }

    @Test
    void shouldReturnProductById() throws Exception {

        mockMvc.perform(get("/products/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("id").value(3))
                .andExpect(jsonPath("name").value("Leffe"))
                .andExpect(jsonPath("itemsInStock").value(10));
    }

    @Test
    void canAddNewProduct() throws Exception {

        AddProductCommand addProductCommand = new AddProductCommand("Tripel Karmeliet", 20);

        String content = objectMapper.writeValueAsString(addProductCommand);
        MvcResult mvcResult = mockMvc.perform(post("/products")
                .contentType(MediaTypes.HAL_JSON)
                .content(content)
        )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(header().string(LOCATION, matchesPattern(BASE_URL + "/" + UUID_PATTERN)))
                .andReturn();

        String id = mvcResult.getResponse().getHeader(LOCATION).split(BASE_URL + "/")[1];

        Optional<Product> foundProduct = productInMemoryStorage.get(id);
        assertThat(foundProduct).isPresent();

        Product product = foundProduct.get();
        assertThat(product.getName()).isEqualTo("Tripel Karmeliet");
        assertThat(product.getItemsInStock()).isEqualTo(20);
    }
}
