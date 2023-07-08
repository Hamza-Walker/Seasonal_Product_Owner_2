package com.codecool.seasonalproductdiscounter.service.products.statistics;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.*;
import java.util.stream.Collectors;


public class ProductStatisticsImpl implements ProductStatistics{
    private final List<Product> products;

    public ProductStatisticsImpl(List<Product> products) {
        this.products = products;
    }

    @Override
    public Optional<Product> getMostExpensive() {
        return products.stream()
                .max(Comparator.comparingDouble(Product::price));
    }

    @Override
    public Optional<Product> getCheapest() {
        return products.stream()
                .min(Comparator.comparingDouble(Product::price));
    }

    @Override
    public OptionalDouble getAveragePrice() {
        return products.stream()
                .mapToDouble(Product::price)
                .average();
    }

    @Override
    public Map<String, Double> getAveragePricesByName() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::name, Collectors.averagingDouble(Product::price)));
    }

    @Override
    public Map<Color, Double> getAveragePricesByColor() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::color, Collectors.averagingDouble(Product::price)));
    }

    @Override
    public Map<Season, Double> getAveragePricesBySeason() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::season, Collectors.averagingDouble(Product::price)));
    }

    @Override
    public Map<PriceRange, Double> getAveragePricesByPriceRange() {
        return products.stream()
                .collect(Collectors.groupingBy(this::calculatePriceRange, Collectors.averagingDouble(Product::price)));
    }
    private PriceRange calculatePriceRange(Product product) {
        // Define the price ranges
        PriceRange smallRange = new PriceRange(0, 10);
        PriceRange mediumRange = new PriceRange(10, 20);
        PriceRange largeRange = new PriceRange(20, Double.MAX_VALUE);

        double price = product.price();
        // Check if the price falls within each range
        if (smallRange.contains(price)) {
            return smallRange;
        } else if (mediumRange.contains(price)) {
            return mediumRange;
        } else if (largeRange.contains(price)) {
            return largeRange;
        } else {
            return null; // or handle the case when the price is not within any range
        }
    }

    @Override
    public Map<String, Integer> getCountByName() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::name, Collectors.summingInt(e -> 1)));
    }

    @Override
    public Map<Color, Integer> getCountByColor() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::color, Collectors.summingInt(e -> 1)));
    }

    @Override
    public Map<Season, Integer> getCountBySeason() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::season, Collectors.summingInt(e -> 1)));
    }

    @Override
    public Map<PriceRange, Integer> getCountByPriceRange() {
        return products.stream()
                .collect(Collectors.groupingBy(this::calculatePriceRange, Collectors.summingInt(e -> 1)));
    }
}
