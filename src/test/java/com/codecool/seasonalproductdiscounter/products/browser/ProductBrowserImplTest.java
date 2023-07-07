package com.codecool.seasonalproductdiscounter.products.browser;

import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBrowserImplTest {

    private ProductBrowser productBrowser;
    private final RandomProductGenerator provider;

    public ProductBrowserImplTest() {
        this.provider = new RandomProductGenerator(50, 10, 70);
    }

    @ParameterizedTest
    @ValueSource(strings = { "skirt", "T-shirt", "jacket", "shirt", "hat", "gloves" })
    public void getByName(String name) {
        Collection<Product> expected = provider.getProducts().stream()
                .filter(p -> p.name().contains(name)).toList();

        Collection<Product> actual = productBrowser.getByName(name);

        assertIterableEquals(expected, actual);
    }
}
