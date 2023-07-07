package com.codecool.seasonalproductdiscounter.service.products.provider;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomProductGenerator implements ProductProvider {

    private static final Random RANDOM = new Random();
    private static final Color[] COLORS = Color.values();
    private static final Season[] SEASONS = Season.values();
    private static final String[] NAMES = {"skirt", "T-shirt", "jacket", "shirt", "hat", "gloves"};

    private final List<Product> products;

    public RandomProductGenerator(int count, double minimumPrice, double maximumPrice) {
        products = generateRandomProducts(count, minimumPrice, maximumPrice);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    private List<Product> generateRandomProducts(int count, double minimumPrice, double maximumPrice) {
    List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Color color = getRandomColor();
            String name = getRandomName(color);
            Season season = getRandomSeason();
            double price = getRandomPrice(minimumPrice,maximumPrice);
            Product product = new Product(i + 1, name, color,season,price);
            products.add(product);
        }
        return products;
    }

    private Color getRandomColor() {
        return COLORS[RANDOM.nextInt(COLORS.length)];
    }

    private String getRandomName(Color color) {
        String type = NAMES[RANDOM.nextInt(NAMES.length)];
        return color.name() + " " + type;
    }

    private Season getRandomSeason() {
        return SEASONS[RANDOM.nextInt(SEASONS.length)];
    }

    private double getRandomPrice(double minimumPrice, double maximumPrice) {
        return minimumPrice + (maximumPrice - minimumPrice) * RANDOM.nextDouble();
    }
}
