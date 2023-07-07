package com.codecool.seasonalproductdiscounter.products.product;

import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RandomProductGeneratorTest {

    @Test
    public void testGenerateRandomProducts_CountZero_ReturnsEmptyList() {
        // Arrange
        int count = 0;
        double minimumPrice = 10.0;
        double maximumPrice = 20.0;
        ProductProvider productProvider = new RandomProductGenerator(count, minimumPrice, maximumPrice);

        // Act
        List<Product> products = productProvider.getProducts();

        // Assert
        Assertions.assertTrue(products.isEmpty());
    }

    @Test
    public void testGenerateRandomProducts_PositiveCount_ReturnsProductsList() {
        // Arrange
        int count = 5;
        double minimumPrice = 10.0;
        double maximumPrice = 20.0;
        ProductProvider productProvider = new RandomProductGenerator(count, minimumPrice, maximumPrice);

        // Act
        List<Product> products = productProvider.getProducts();

        // Assert
        Assertions.assertEquals(count, products.size());
    }

    @Test
    public void testGenerateRandomProducts_PriceRange_ReturnsProductsInRange() {
        // Arrange
        int count = 10;
        double minimumPrice = 10.0;
        double maximumPrice = 20.0;
        ProductProvider productProvider = new RandomProductGenerator(count, minimumPrice, maximumPrice);

        // Act
        List<Product> products = productProvider.getProducts();

        // Assert
        for (Product product : products) {
            double price = product.price();
            Assertions.assertTrue(price >= minimumPrice && price <= maximumPrice);
        }
    }

    @Test
    public void testGenerateRandomProducts_NameFormat_ReturnsValidNameFormat() {
        // Arrange
        int count = 3;
        double minimumPrice = 10.0;
        double maximumPrice = 20.0;
        ProductProvider productProvider = new RandomProductGenerator(count, minimumPrice, maximumPrice);

        // Act
        List<Product> products = productProvider.getProducts();

        // Assert
        for (Product product : products) {
            String name = product.name();
            Assertions.assertTrue(name.matches("[A-Z]+ [a-z]+"));
        }
    }
}
