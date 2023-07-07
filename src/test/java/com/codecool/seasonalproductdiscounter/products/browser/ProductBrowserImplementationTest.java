package com.codecool.seasonalproductdiscounter.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowserImplementation;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ProductBrowserImplementationTest {
    private ProductBrowser productBrowser;

    @BeforeEach
    public void setUp() {
        int count = 5;
        double minimumPrice = 10.0;
        double maximumPrice = 20.0;
        ProductProvider productProvider = new RandomProductGenerator(count, minimumPrice, maximumPrice);
        List<Product> products = productProvider.getProducts();
        productBrowser = new ProductBrowserImplementation(products);
    }

    @Test
    public void testGetAll() {
        List<Product> allProducts = new ArrayList<>(productBrowser.getAll());
        Assertions.assertEquals(5, allProducts.size());
    }

    @Test
    public void testGetByName() {
        String name = productBrowser.getAll().stream().findFirst().orElseThrow().name();
        List<Product> byName = new ArrayList<>(productBrowser.getByName(name));
        Assertions.assertEquals(1, byName.size());
        Assertions.assertEquals(name, byName.get(0).name());
    }

    @Test
    public void testGetByColor() {
        Color color = productBrowser.getAll().stream().findFirst().orElseThrow().color();
        List<Product> byColor = new ArrayList<>(productBrowser.getByColor(color));
        Assertions.assertTrue(byColor.stream().allMatch(product -> product.color() == color));
    }

    @Test
    public void testGetBySeason() {
        Season season = productBrowser.getAll().stream().findFirst().orElseThrow().season();
        List<Product> bySeason = new ArrayList<>(productBrowser.getBySeason(season));
        Assertions.assertTrue(bySeason.stream().allMatch(product -> product.season() == season));
    }

    @Test
    public void testGetByPriceSmallerThan() {
        double price = 15.0;
        List<Product> byPriceSmallerThan = new ArrayList<>(productBrowser.getByPriceSmallerThan(price));
        Assertions.assertTrue(byPriceSmallerThan.stream().allMatch(product -> product.price() < price));
    }

    @Test
    public void testGetByPriceGreaterThan() {
        double price = 10.0;
        List<Product> byPriceGreaterThan = new ArrayList<>(productBrowser.getByPriceGreaterThan(price));
        Assertions.assertTrue(byPriceGreaterThan.stream().allMatch(product -> product.price() > price));
    }

    @Test
    public void testGetByPriceRange() {
        double minimumPrice = 10.0;
        double maximumPrice = 20.0;
        List<Product> byPriceRange = new ArrayList<>(productBrowser.getByPriceRange(minimumPrice, maximumPrice));
        Assertions.assertTrue(byPriceRange.stream()
                .allMatch(product -> product.price() >= minimumPrice && product.price() <= maximumPrice));
    }

    @Test
    public void testGroupByName() {
        Map<String, List<Product>> groupedByName = productBrowser.groupByName();
        Assertions.assertEquals(5, groupedByName.size());
    }

    @Test
    public void testGroupByColor() {
        Map<String, List<Product>> groupedByColor = productBrowser.groupByColor();
        Assertions.assertEquals(5, groupedByColor.size());
    }

    @Test
    public void testGroupBySeason() {
        Map<String, List<Product>> groupedBySeason = productBrowser.groupBySeason();
        Assertions.assertEquals(5, groupedBySeason.size());
    }

    @Test
    public void testGroupByPriceRange() {
        Map<PriceRange, List<Product>> groupedByPriceRange = productBrowser.groupByPriceRange();
        Assertions.assertEquals(3, groupedByPriceRange.size());
    }

    @Test
    public void testOrderByName() {
        List<Product> orderedByName = new ArrayList<>(productBrowser.orderByName());
        List<Product> expected = new ArrayList<>(productBrowser.getAll());
        expected.sort(Comparator.comparing(Product::name));
        Assertions.assertEquals(expected, orderedByName);
    }

    @Test
    public void testOrderByColor() {
        List<Product> orderedByColor = new ArrayList<>(productBrowser.orderByColor());
        List<Product> expected = new ArrayList<>(productBrowser.getAll());
        expected.sort(Comparator.comparing(Product::color));
        Assertions.assertEquals(expected, orderedByColor);
    }

    @Test
    public void testOrderBySeason() {
        List<Product> orderedBySeason = new ArrayList<>(productBrowser.orderBySeason());
        List<Product> expected = new ArrayList<>(productBrowser.getAll());
        expected.sort(Comparator.comparing(Product::season));
        Assertions.assertEquals(expected, orderedBySeason);
    }

    @Test
    public void testOrderByPrice() {
        List<Product> orderedByPrice = new ArrayList<>(productBrowser.orderByPrice());
        List<Product> expected = new ArrayList<>(productBrowser.getAll());
        expected.sort(Comparator.comparing(Product::price));
        Assertions.assertEquals(expected, orderedByPrice);
    }
}
