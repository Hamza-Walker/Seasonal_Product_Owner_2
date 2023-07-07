package com.codecool.seasonalproductdiscounter.service.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductBrowserImplementation implements ProductBrowser {
    private final Collection <Product> products;

    public ProductBrowserImplementation(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public Collection<Product> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Collection<Product> getByName(String name) {
        return products.stream()
                .filter(product -> product.name().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getByColor(Color color) {
        return products.stream()
                .filter( product -> product.color() == color)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getBySeason(Season season) {
        return products.stream()
                .filter(product -> product.season() == season)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getByPriceSmallerThan(double price) {
        return products.stream()
                .filter(product -> product.price() > price)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getByPriceGreaterThan(double price) {
        return products.stream()
                .filter(product -> product.price()< price)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> getByPriceRange(double minimumPrice, double maximumPrice) {
        return products.stream()
                .filter(product -> product.price() >= minimumPrice && product.price() <= maximumPrice)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Product>> groupByName() {
        return products.stream()
                .collect(Collectors.groupingBy(product -> product.toString().split("'")[1]));
    }

    @Override
    public Map<String, List<Product>> groupByColor() {
        return products.stream()
                .collect(Collectors.groupingBy(product -> product.toString().split("'")[2]));
    }

    @Override
    public Map<String, List<Product>> groupBySeason() {
        return products.stream()
                .collect(Collectors.groupingBy(product -> product.toString().split("'")[3]));
    }
    @Override
    public Map<PriceRange, List<Product>> groupByPriceRange() {
        return products.stream()
                .collect(Collectors.groupingBy(product -> getPriceRange(product.price())));
    }

    private PriceRange getPriceRange(double price) {
        // Define the price ranges
        PriceRange smallRange = new PriceRange(0, 10);
        PriceRange mediumRange = new PriceRange(10, 20);
        PriceRange largeRange = new PriceRange(20, Double.MAX_VALUE);

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
    public Collection<Product> orderByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> orderByColor() {
        return products.stream()
                .sorted(Comparator.comparing(Product::color))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> orderBySeason() {
        return products.stream()
                .sorted(Comparator.comparing(Product::season))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Product> orderByPrice() {
        return products.stream()
                .sorted(Comparator.comparing(Product::price))
                .collect(Collectors.toList());
    }
}
