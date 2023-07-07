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
        return null;
    }

    private Color getRandomColor() {
        return null;
    }

    private String getRandomName(Color color) {
        return null;
    }

    private Season getRandomSeason() {
        return null;
    }

    private double getRandomPrice(double minimumPrice, double maximumPrice) {
        return 0;
    }
}
