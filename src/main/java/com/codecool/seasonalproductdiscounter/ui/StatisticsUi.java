package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatisticsImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StatisticsUi {

    public static void main(String[] args) {

        ProductProvider productProvider = new RandomProductGenerator(30, 10.0, 100.0);
        List<Product> products = productProvider.getProducts();


        ProductStatisticsImpl productStatistics = new ProductStatisticsImpl(products);

        // Print the results of the methods
        System.out.println("Most Expensive Product:");
        Optional<Product> mostExpensive = productStatistics.getMostExpensive();
        mostExpensive.ifPresent(System.out::println);

        System.out.println("Cheapest Product:");
        Optional<Product> cheapest = productStatistics.getCheapest();
        cheapest.ifPresent(System.out::println);

        System.out.println("Average Price: " + productStatistics.getAveragePrice().orElse(0.0));

        System.out.println("Average Prices by Name:");
        Map<String, Double> averagePricesByName = productStatistics.getAveragePricesByName();
        averagePricesByName.forEach((name, averagePrice) -> System.out.println(name + ": " + averagePrice));

        System.out.println("Average Prices by Color:");
        Map<Color, Double> averagePricesByColor = productStatistics.getAveragePricesByColor();
        averagePricesByColor.forEach((color, averagePrice) -> System.out.println(color + ": " + averagePrice));

        System.out.println("Average Prices by Season:");
        Map<Season, Double> averagePricesBySeason = productStatistics.getAveragePricesBySeason();
        averagePricesBySeason.forEach((season, averagePrice) -> System.out.println(season + ": " + averagePrice));

        System.out.println("Average Prices by Price Range:");
        Map<PriceRange, Double> averagePricesByPriceRange = productStatistics.getAveragePricesByPriceRange();
        averagePricesByPriceRange.forEach((priceRange, averagePrice) -> System.out.println(priceRange + ": " + averagePrice));

        System.out.println("Count by Name:");
        Map<String, Integer> countByName = productStatistics.getCountByName();
        countByName.forEach((name, count) -> System.out.println(name + ": " + count));

        System.out.println("Count by Color:");
        Map<Color, Integer> countByColor = productStatistics.getCountByColor();
        countByColor.forEach((color, count) -> System.out.println(color + ": " + count));

        System.out.println("Count by Season:");
        Map<Season, Integer> countBySeason = productStatistics.getCountBySeason();
        countBySeason.forEach((season, count) -> System.out.println(season + ": " + count));

        System.out.println("Count by Price Range:");
        Map<PriceRange, Integer> countByPriceRange = productStatistics.getCountByPriceRange();
        countByPriceRange.forEach((priceRange, count) -> System.out.println(priceRange + ": " + count));
    }
}

