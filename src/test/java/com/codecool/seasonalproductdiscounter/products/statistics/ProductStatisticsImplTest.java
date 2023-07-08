package com.codecool.seasonalproductdiscounter.products.statistics;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatisticsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class ProductStatisticsImplTest {
    private final List<Product> products = Arrays.asList(
            new Product(1, "Product A", Color.RED, Season.SUMMER, 100.0),
            new Product(2, "Product B", Color.BLUE, Season.WINTER, 50.0),
            new Product(3, "Product C", Color.GREEN, Season.SPRING, 75.0),
            new Product(4, "Product D", Color.RED, Season.SUMMER, 80.0),
            new Product(5, "Product E", Color.BLUE, Season.WINTER, 120.0),
            new Product(6, "Product F", Color.GREEN, Season.SPRING, 90.0)
    );

    @Test
    void testGetMostExpensive() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Optional<Product> mostExpensive = productStatistics.getMostExpensive();
        Assertions.assertTrue(mostExpensive.isPresent());
        Assertions.assertEquals(120.0, mostExpensive.get().price());
    }

    @Test
    void testGetCheapest() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Optional<Product> cheapest = productStatistics.getCheapest();
        Assertions.assertTrue(cheapest.isPresent());
        Assertions.assertEquals(50.0, cheapest.get().price());
    }

    @Test
    void testGetAveragePrice() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        OptionalDouble averagePrice = productStatistics.getAveragePrice();
        Assertions.assertTrue(averagePrice.isPresent());
        Assertions.assertEquals(88.33333333333333, averagePrice.getAsDouble(), 0.0001);
    }

    @Test
    void testGetAveragePricesByName() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<String, Double> averagePricesByName = productStatistics.getAveragePricesByName();
        Assertions.assertEquals(3, averagePricesByName.size());
        Assertions.assertEquals(100.0, averagePricesByName.get("Product A"));
        Assertions.assertEquals(85.0, averagePricesByName.get("Product B"));
        Assertions.assertEquals(82.5, averagePricesByName.get("Product C"));
    }

    @Test
    void testGetAveragePricesByColor() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<Color, Double> averagePricesByColor = productStatistics.getAveragePricesByColor();
        Assertions.assertEquals(3, averagePricesByColor.size());
        Assertions.assertEquals(90.0, averagePricesByColor.get(Color.RED));
        Assertions.assertEquals(85.0, averagePricesByColor.get(Color.BLUE));
        Assertions.assertEquals(82.5, averagePricesByColor.get(Color.GREEN));
    }

    @Test
    void testGetAveragePricesBySeason() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<Season, Double> averagePricesBySeason = productStatistics.getAveragePricesBySeason();
        Assertions.assertEquals(3, averagePricesBySeason.size());
        Assertions.assertEquals(90.0, averagePricesBySeason.get(Season.SUMMER));
        Assertions.assertEquals(85.0, averagePricesBySeason.get(Season.WINTER));
        Assertions.assertEquals(82.5, averagePricesBySeason.get(Season.SPRING));
    }

    @Test
    void testGetAveragePricesByPriceRange() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<PriceRange, Double> averagePricesByPriceRange = productStatistics.getAveragePricesByPriceRange();
        Assertions.assertEquals(3, averagePricesByPriceRange.size());
        Assertions.assertEquals(90.0, averagePricesByPriceRange.get(new PriceRange(0, 10)));
        Assertions.assertEquals(85.0, averagePricesByPriceRange.get(new PriceRange(10, 20)));
        Assertions.assertEquals(82.5, averagePricesByPriceRange.get(new PriceRange(20, Double.MAX_VALUE)));
    }

    @Test
    void testGetCountByName() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<String, Integer> countByName = productStatistics.getCountByName();
        Assertions.assertEquals(3, countByName.size());
        Assertions.assertEquals(1, countByName.get("Product A"));
        Assertions.assertEquals(1, countByName.get("Product B"));
        Assertions.assertEquals(1, countByName.get("Product C"));
    }

    @Test
    void testGetCountByColor() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<Color, Integer> countByColor = productStatistics.getCountByColor();
        Assertions.assertEquals(3, countByColor.size());
        Assertions.assertEquals(2, countByColor.get(Color.RED));
        Assertions.assertEquals(2, countByColor.get(Color.BLUE));
        Assertions.assertEquals(2, countByColor.get(Color.GREEN));
    }

    @Test
    void testGetCountBySeason() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<Season, Integer> countBySeason = productStatistics.getCountBySeason();
        Assertions.assertEquals(3, countBySeason.size());
        Assertions.assertEquals(2, countBySeason.get(Season.SUMMER));
        Assertions.assertEquals(2, countBySeason.get(Season.WINTER));
        Assertions.assertEquals(2, countBySeason.get(Season.SPRING));
    }

    @Test
    void testGetCountByPriceRange() {
        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);
        Map<PriceRange, Integer> countByPriceRange = productStatistics.getCountByPriceRange();
        Assertions.assertEquals(3, countByPriceRange.size());
        Assertions.assertEquals(2, countByPriceRange.get(new PriceRange(0, 10)));
        Assertions.assertEquals(2, countByPriceRange.get(new PriceRange(10, 20)));
        Assertions.assertEquals(2, countByPriceRange.get(new PriceRange(20, Double.MAX_VALUE)));
    }
}
